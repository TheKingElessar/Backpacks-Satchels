package de.eydamos.backpack.helper;

import cofh.thermalexpansion.item.ItemSatchel;
import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.data.BackpackSave;
import de.eydamos.backpack.data.PlayerSave;
import de.eydamos.backpack.init.BackpackItems;
import de.eydamos.backpack.item.ESize;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import de.eydamos.backpack.util.GeneralUtil;
import de.eydamos.backpack.util.NBTItemStackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class BackpackHelper {
    public static void initialize(ItemStack backpack, ItemStack leather, ItemStack frame) {
        TierLeather tierLeather = TierLeather.getTierByItemStack(leather);
        TierFrame tierFrame = TierFrame.getTierByItemStack(frame);

        initialize(backpack, tierLeather, tierFrame);
    }

    public static void initialize(ItemStack backpack, TierLeather leather, TierFrame frame) {
        if (!isBackpack(backpack)) {
            Backpack.logger.error("ItemStack is null or not a backpack.");
            return;
        }

        ESize size = ESize.getSizeByBackpack(backpack);
        if (size == null || leather == null || frame == null) {
            Backpack.logger.error("Mandatory data missing can't initialize backpack.");
            return;
        }

        leather.setTier(backpack);
        frame.setTier(backpack);

        int slots = leather.getBaseSlots() * size.getSlotMultiplier();
        NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS, slots);
        NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_USED, 0);

        if (size.equals(ESize.SMALL) && leather.equals(TierLeather.II)) {
            NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_PER_ROW, 6);
        } else if (size.equals(ESize.MEDIUM) && leather.equals(TierLeather.I)) {
            NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_PER_ROW, 6);
        } else if (size.equals(ESize.MEDIUM) && leather.equals(TierLeather.II)) {
            NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_PER_ROW, 8);
        } else {
            NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_PER_ROW, 9);
        }

        NBTItemStackUtil.setInteger(backpack, Constants.NBT.MODULE_SLOTS, size.getModuleSlots());

        NBTItemStackUtil.setString(backpack, Constants.NBT.UID, UUID.randomUUID().toString());
    }

    public static int getSlots(ItemStack itemStack) {
        if (isBackpack(itemStack)) {
            return NBTItemStackUtil.getInteger(itemStack, Constants.NBT.SLOTS);
        }

        return 0;
    }

    public static int getSlotsUsed(ItemStack itemStack) {
        if (isBackpack(itemStack)) {
            return NBTItemStackUtil.getInteger(itemStack, Constants.NBT.SLOTS_USED);
        }

        return 0;
    }

    public static int getSlotsPerRow(ItemStack itemStack) {
        if (isBackpack(itemStack)) {
            return NBTItemStackUtil.getInteger(itemStack, Constants.NBT.SLOTS_PER_ROW);
        }

        return 9;
    }

    public static String getUUID(ItemStack itemStack) {
        if (isBackpack(itemStack)) {
            return NBTItemStackUtil.getString(itemStack, Constants.NBT.UID);
        }

        return "";
    }

    public static IInventory getInventory(EntityPlayer player, boolean heldItem) {
        ItemStack itemStack = getBackpackFromPlayer(player, heldItem);

        if (isBackpack(itemStack)) {
            return BackpackSave.loadBackpack(player.world, itemStack, player, heldItem);
        }

        return new BackpackSave();
    }
    
    public static boolean isBackpack(ItemStack itemStack)
    {
        if (!itemStack.isEmpty())
        {
            if (itemStack.getItem() == BackpackItems.backpack)
            {
                return true;
            }
            else if (itemStack.getItem() == ItemSatchel.satchelBasic.getItem())
            {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getBackpackFromPlayer(EntityPlayer player, boolean heldItem) {
        ItemStack backpack;
        if (heldItem) {
            backpack = player.getHeldItemMainhand();
        } else {
            if (GeneralUtil.isServerSide(player.world)) {
                PlayerSave playerSave = PlayerSave.loadPlayer(player.world, player);
                backpack = playerSave.getBackpack();
            } else {
                backpack = Backpack.proxy.getClientBackpack(player);
            }
        }

        if (isBackpack(backpack)) {
            return backpack;
        }

        return ItemStack.EMPTY;
    }

    public static void setSlotsUsed(ItemStack backpack, int slotsUsed) {
        NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_USED, slotsUsed);
    }
}
