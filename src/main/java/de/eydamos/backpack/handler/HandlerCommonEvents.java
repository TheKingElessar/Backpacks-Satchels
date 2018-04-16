package de.eydamos.backpack.handler;

import de.eydamos.backpack.data.PlayerSave;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
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

    @SubscribeEvent
    public void playerDies(PlayerDropsEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        PlayerSave playerSave = PlayerSave.loadPlayer(player.world, player);
        ItemStack backpack = playerSave.getBackpack();
        if (!backpack.isEmpty()) {
            event.getDrops().add(new EntityItem(player.world, player.posX, player.posY, player.posZ, backpack));
            playerSave.setInventorySlotContents(0, ItemStack.EMPTY);
        }
    }
}
