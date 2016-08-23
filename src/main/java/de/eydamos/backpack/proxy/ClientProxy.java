package de.eydamos.backpack.proxy;

import de.eydamos.backpack.handler.HandlerClientEvents;
import de.eydamos.backpack.handler.HandlerInputEvents;
import de.eydamos.backpack.misc.Bootstrap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItems() {
        super.registerItems();
        Bootstrap.registerVariants();
    }

    @Override
    public void registerIcons() {
        Bootstrap.registerIcons();
    }

    @Override
    public void registerHandlers() {
        super.registerHandlers();

        MinecraftForge.EVENT_BUS.register(new HandlerInputEvents());
        MinecraftForge.EVENT_BUS.register(new HandlerClientEvents());
    }

    @Override
    public void registerKeybindings() {
        ClientRegistry.registerKeyBinding(HandlerInputEvents.personalBackpack);
    }
}
