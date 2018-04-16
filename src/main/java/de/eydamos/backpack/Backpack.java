package de.eydamos.backpack;

import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.network.PacketHandlerBackpack;
import de.eydamos.backpack.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION, certificateFingerprint = Constants.FINGERPRINT/*, guiFactory = Constants.CLASS_GUI_FACTORY*/)
public class Backpack {
    @Mod.Instance(Constants.MOD_ID)
    public static Backpack instance;

    @SidedProxy(clientSide = Constants.CLASS_PROXY_CLIENT, serverSide = Constants.CLASS_PROXY_SERVER)
    public static CommonProxy proxy;

    public static final PacketHandlerBackpack packetHandler = new PacketHandlerBackpack();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // get the configuration and let forge guess the filename
        //ConfigurationBackpack.init(event.getSuggestedConfigurationFile());
        
        // key bindings
        proxy.registerKeybindings();

        FMLInterModComms.sendRuntimeMessage(Constants.MOD_ID, "VersionChecker", "addVersionCheck", Constants.UPDATE_FILE);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // registerItems all Handlers
        proxy.registerHandlers();
    }
}
