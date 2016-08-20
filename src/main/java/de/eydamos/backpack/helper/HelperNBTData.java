package de.eydamos.backpack.helper;

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

        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        if (frame.getItem() == Constants.Items.FRAME.getItem()) {
            switch(frame.getItemDamage()) {
                case 0:
                    itemStack.getTagCompound().setString(Constants.NBT.FRAME_TIER, "I");
                    break;
                case 1:
                    itemStack.getTagCompound().setString(Constants.NBT.FRAME_TIER, "II");
                    break;
                case 2:
                    itemStack.getTagCompound().setString(Constants.NBT.FRAME_TIER, "III");
                    break;
            }
        }

        if (!getFrameTier(frame).isEmpty()) {
            itemStack.getTagCompound().setString(Constants.NBT.FRAME_TIER, getFrameTier(frame));
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

        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        if (leather.getItem() == Items.rabbit_hide) {
            itemStack.getTagCompound().setString(Constants.NBT.LEATHER_TIER, "I");
        } else if (leather.getItem() == Items.leather) {
            itemStack.getTagCompound().setString(Constants.NBT.LEATHER_TIER, "II");
        } else if (leather.getItem() == Constants.Items.LEATHER.getItem() && leather.getItemDamage() == 1) {
            itemStack.getTagCompound().setString(Constants.NBT.LEATHER_TIER, "III");
        }

        if (!getLeatherTier(leather).isEmpty()) {
            itemStack.getTagCompound().setString(Constants.NBT.LEATHER_TIER, getLeatherTier(leather));
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
