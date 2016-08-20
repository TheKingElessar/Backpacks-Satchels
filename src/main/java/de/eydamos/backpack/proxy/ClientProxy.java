package de.eydamos.backpack.proxy;

import de.eydamos.backpack.handler.KeyInputHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerHandlers() {
        super.registerHandlers();
        //FMLCommonHandler.instance().bus().registerItems(new KeyInputHandler());

        /*if(ConfigurationBackpack.RENDER_BACKPACK_MODEL) {
            EventHandlerClientOnly eventHandlerClient = new EventHandlerClientOnly();
            MinecraftForge.EVENT_BUS.registerItems(eventHandlerClient);
            FMLCommonHandler.instance().bus().registerItems(eventHandlerClient);
        }*/
    }

    @Override
    public void registerKeybindings() {
        ClientRegistry.registerKeyBinding(KeyInputHandler.personalBackpack);
    }

    @Override
    public void addNeiSupport() {
        /*try {
            Class API = Class.forName("codechicken.nei.api.API");
            Class IOverlayHandler = Class.forName("codechicken.nei.api.IOverlayHandler");
            Method registerGuiOverlayHandler = API.getDeclaredMethod("registerGuiOverlayHandler", new Class[] { Class.class, IOverlayHandler, String.class });

            registerGuiOverlayHandler.invoke(API, new Object[] { GuiWorkbenchBackpack.class, new OverlayHandlerBackpack(), "crafting" });

            ConfigurationBackpack.NEISupport = true;

            FMLLog.log(Constants.MOD_ID, Level.INFO, "[Backpacks] NEI Support enabled");
        }
        catch (Exception e) {
            FMLLog.log(Constants.MOD_ID, Level.INFO, "[Backpacks] NEI Support couldn't be enabled");
        }*/
    }
}
