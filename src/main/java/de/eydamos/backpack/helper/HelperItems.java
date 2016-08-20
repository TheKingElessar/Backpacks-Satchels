package de.eydamos.backpack.helper;

import de.eydamos.backpack.misc.BackpackItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HelperItems {
    public static boolean isLeather(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() == Items.rabbit_hide) {
            return true;
        }

        if (itemStack.getItem() == Items.leather) {
            return true;
        }

        if (itemStack.getItem() == BackpackItems.tanned_leather) {
            return true;
        }

        return false;
    }

    public static boolean isSameLeatherType(ItemStack itemStackA, ItemStack itemStackB) {
        if (itemStackA == null || itemStackB == null) {
            return false;
        }

        if (itemStackA.getItem() != itemStackB.getItem()) {
            return false;
        }

        return itemStackA.getItemDamage() == itemStackB.getItemDamage();
    }

    public static boolean isTopPiece(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() == BackpackItems.backpack_piece && itemStack.getItemDamage() == 0) {
            return true;
        }

        return false;
    }

    public static boolean isMiddlePiece(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() == BackpackItems.backpack_piece && itemStack.getItemDamage() == 1) {
            return true;
        }

        return false;
    }

    public static boolean isBottomPiece(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() == BackpackItems.backpack_piece && itemStack.getItemDamage() == 2) {
            return true;
        }

        return false;
    }

    public static boolean sameTier(ItemStack itemStackA, ItemStack itemStackB) {
        if (itemStackA == null || itemStackB == null) {
            return false;
        }

        if (!HelperNBTData.getFrameTier(itemStackA).equals(HelperNBTData.getFrameTier(itemStackB))) {
            return false;
        }

        if (!HelperNBTData.getLeatherTier(itemStackA).equals(HelperNBTData.getLeatherTier(itemStackB))) {
            return false;
        }

        return true;
    }
}
