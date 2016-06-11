package de.eydamos.backpack.proxy;

import net.minecraftforge.common.MinecraftForge;
import de.eydamos.backpack.Backpack;
import net.minecraftforge.fml.common.FMLCommonHandler;

public abstract class CommonProxy implements IProxy {
    @Override
    public void registerHandlers() {
        //Backpack.packetHandler.initialise();

        //EventHandlerBackpack eventHandler = new EventHandlerBackpack();

        //FMLCommonHandler.instance().bus().register(eventHandler);
        //MinecraftForge.EVENT_BUS.register(eventHandler);
    }

    @Override
    public void registerKeybindings() {}

    @Override
    public void addNeiSupport() {}
}