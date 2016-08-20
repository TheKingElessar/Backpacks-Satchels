package de.eydamos.backpack.helper;

import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class HelperNBTData {
    public static String getFrameTier(ItemStack itemStack) {
        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound != null && nbtTagCompound.hasKey(Constants.NBT.FRAME_TIER)) {
            return nbtTagCompound.getString(Constants.NBT.FRAME_TIER);
        }

        return "";
    }

    public static void setFrameTier(ItemStack itemStack, ItemStack frame) {
        if (itemStack == null || frame == null) {
            return;
        }

        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound == null) {
            nbtTagCompound = new NBTTagCompound();
        }

        if (frame.getItem() == BackpackItems.backpack_frame) {
            switch (frame.getItemDamage()) {
                case 0:
                    nbtTagCompound.setString(Constants.NBT.FRAME_TIER, "I");
                    break;
                case 1:
                    nbtTagCompound.setString(Constants.NBT.FRAME_TIER, "II");
                    break;
                case 2:
                    nbtTagCompound.setString(Constants.NBT.FRAME_TIER, "III");
                    break;
            }
        }

        if (!getFrameTier(frame).isEmpty()) {
            nbtTagCompound.setString(Constants.NBT.FRAME_TIER, getFrameTier(frame));
        }

        if (!nbtTagCompound.hasNoTags()) {
            itemStack.setTagCompound(nbtTagCompound);
        }
    }

    public static String getLeatherTier(ItemStack itemStack) {
        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound != null && nbtTagCompound.hasKey(Constants.NBT.LEATHER_TIER)) {
            return nbtTagCompound.getString(Constants.NBT.LEATHER_TIER);
        }

        return "";
    }

    public static void setLeatherTier(ItemStack itemStack, ItemStack leather) {
        if (itemStack == null || leather == null) {
            return;
        }

        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound == null) {
            nbtTagCompound = new NBTTagCompound();
        }

        if (leather.getItem() == Items.rabbit_hide) {
            nbtTagCompound.setString(Constants.NBT.LEATHER_TIER, "I");
        } else if (leather.getItem() == Items.leather) {
            nbtTagCompound.setString(Constants.NBT.LEATHER_TIER, "II");
        } else if (leather.getItem() == BackpackItems.tanned_leather) {
            nbtTagCompound.setString(Constants.NBT.LEATHER_TIER, "III");
        }

        if (!getLeatherTier(leather).isEmpty()) {
            nbtTagCompound.setString(Constants.NBT.LEATHER_TIER, getLeatherTier(leather));
        }

        if (!nbtTagCompound.hasNoTags()) {
            itemStack.setTagCompound(nbtTagCompound);
        }
    }

    public static void addTooltip(ItemStack itemStack, List<String> tooltip) {
        if (!getFrameTier(itemStack).isEmpty()) {
            tooltip.add("Frame Tier: " + getFrameTier(itemStack));
        }

        if (!getLeatherTier(itemStack).isEmpty()) {
            tooltip.add("Leather Tier: " + getLeatherTier(itemStack));
        }
    }
}
