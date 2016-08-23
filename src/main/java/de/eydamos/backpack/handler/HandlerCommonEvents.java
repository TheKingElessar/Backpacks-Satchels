package de.eydamos.backpack.handler;

import de.eydamos.backpack.misc.Constants;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class HandlerCommonEvents {
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        try {
            File dataDir = new File(DimensionManager.getCurrentSaveRootDirectory(), "data");

            File backpackDir = new File(dataDir, Constants.INVENTORIES_PATH);
            if (!backpackDir.exists()) {
                if (!backpackDir.mkdirs()) {
                    FMLLog.warning("Unable to create backpack/inventory folder. Saving backpack inventory will fail!");
                }
            }

            File playersDir = new File(dataDir, Constants.PLAYERS_PATH);
            if (!playersDir.exists()) {
                if (!playersDir.mkdirs()) {
                    FMLLog.warning("Unable to create backpack/player folder. Saving player specific backpack data will fail!");
                }
            }
        } catch (Exception e) {
            FMLLog.info("Unable to check if backpack folders are present. If you are a client connecting to a server ignore this info.");
        }
    }
}
