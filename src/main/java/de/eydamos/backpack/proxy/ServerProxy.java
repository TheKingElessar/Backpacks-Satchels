package de.eydamos.backpack.proxy;

import de.eydamos.backpack.handler.HandlerServerEvents;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy {
    @Override
    public void registerHandlers() {
        super.registerHandlers();

        MinecraftForge.EVENT_BUS.register(new HandlerServerEvents());
    }
}
