package de.eydamos.backpack.handler;

import de.eydamos.backpack.misc.Constants;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class HandlerCommonEvents {
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        File dataDir = new File(DimensionManager.getCurrentSaveRootDirectory(), "data");

        File backpackDir = new File(dataDir, Constants.INVENTORIES_PATH);
        if (!backpackDir.exists()) {
            backpackDir.mkdirs();
        }

        File playersDir = new File(dataDir, Constants.PLAYERS_PATH);
        if (!playersDir.exists()) {
            playersDir.mkdirs();
        }
    }
}
