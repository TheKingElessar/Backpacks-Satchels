package de.eydamos.backpack;

import de.eydamos.backpack.init.Configurations;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.network.PacketHandlerBackpack;
import de.eydamos.backpack.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION, certificateFingerprint = Constants.FINGERPRINT, guiFactory = Constants.CLASS_GUI_FACTORY, dependencies = "required-after:thermalexpansion@[5.5.0,)")
public class Backpack {
    @Mod.Instance(Constants.MOD_ID)
    public static Backpack instance;

    @SidedProxy(clientSide = Constants.CLASS_PROXY_CLIENT, serverSide = Constants.CLASS_PROXY_SERVER)
    public static CommonProxy proxy;

    public static Logger logger;

    public static final PacketHandlerBackpack packetHandler = new PacketHandlerBackpack();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.registerResourceLoader();

        File configFile = event.getSuggestedConfigurationFile();
        Configurations.configExists = configFile.exists();
        Configurations.config = new Configuration(configFile);
        Configurations.config.load();
        Configurations.refreshConfig();

        // key bindings
        proxy.registerKeybindings();

        FMLInterModComms.sendRuntimeMessage(
            Constants.MOD_ID,
            "VersionChecker",
            "addVersionCheck",
            Constants.UPDATE_FILE
        );
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // register all handlers
        proxy.registerHandlers();
    }
}
