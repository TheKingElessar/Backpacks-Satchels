package de.eydamos.backpack.proxy;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.handler.HandlerGui;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public abstract class CommonProxy implements ProxyInterface {
    @Override
    public void registerHandlers() {
        Backpack.packetHandler.initialise();

        NetworkRegistry.INSTANCE.registerGuiHandler(Backpack.instance, new HandlerGui());
    }
}
