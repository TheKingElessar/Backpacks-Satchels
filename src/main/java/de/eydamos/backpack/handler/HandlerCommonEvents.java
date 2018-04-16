package de.eydamos.backpack.handler;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.data.PlayerSave;
import de.eydamos.backpack.init.Configurations;
import de.eydamos.backpack.item.ItemBackpack;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.util.List;

public class HandlerCommonEvents {
    @SubscribeEvent
    public void serverTick(TickEvent.PlayerTickEvent event) {
        if (Configurations.MAX_BACKPACK_AMOUNT > 0) {
            if (event.side == Side.SERVER) {
                EntityPlayerMP player = (EntityPlayerMP) event.player;

                int counter = 0;
                if (!PlayerSave.loadPlayer(player.world, player).getBackpack().isEmpty()) {
                    counter++;
                }

                List<ItemStack> inventory = player.inventory.mainInventory;
                for (ItemStack itemStack : inventory) {
                    if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemBackpack) {
                        counter++;
                        if (counter > Configurations.MAX_BACKPACK_AMOUNT) {
                            EntityItem item = player.entityDropItem(itemStack.copy(), 0);
                            if (item != null) {
                                item.setPickupDelay(40);
                            }
                            itemStack.shrink(1);
                        }
                    }
                }

                counter -= Configurations.MAX_BACKPACK_AMOUNT;
                if (counter > 0) {
                    ITextComponent message = new TextComponentString("[Backpacks] ");
                    message.appendSibling(new TextComponentTranslation(Localizations.MESSAGE_ALLOWED_BACKPACKS, Configurations.MAX_BACKPACK_AMOUNT));
                    player.sendMessage(message);
                    message = new TextComponentString("[Backpacks] ").appendSibling(new TextComponentTranslation(Localizations.MESSAGE_DROPPED_BACKPACKS, counter));
                    player.sendMessage(message);
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        try {
            File dataDir = new File(DimensionManager.getCurrentSaveRootDirectory(), "data");

            File backpackDir = new File(dataDir, Constants.INVENTORIES_PATH);
            if (!backpackDir.exists()) {
                if (!backpackDir.mkdirs()) {
                    Backpack.logger.warn("Unable to create backpack/inventory folder. Saving backpack inventory will fail!");
                }
            }

            File playersDir = new File(dataDir, Constants.PLAYERS_PATH);
            if (!playersDir.exists()) {
                if (!playersDir.mkdirs()) {
                    Backpack.logger.warn("Unable to create backpack/player folder. Saving player specific backpack data will fail!");
                }
            }
        } catch (Exception e) {
            Backpack.logger.info("Unable to check if backpack folders are present. If you are a client connecting to a server ignore this info.");
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
