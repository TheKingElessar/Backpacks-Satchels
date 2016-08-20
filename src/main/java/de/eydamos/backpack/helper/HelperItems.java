package de.eydamos.backpack.helper;

import de.eydamos.backpack.item.EPiece;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import net.minecraft.item.ItemStack;

public class HelperItems {
    public static boolean isLeather(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (TierLeather.getTierByItemStack(itemStack, true) != null) {
            return true;
        }

        return false;
    }

    public static boolean isSameLeatherType(ItemStack itemStackA, ItemStack itemStackB) {
        if (itemStackA == null || itemStackB == null) {
            return false;
        }

        return TierLeather.itemStackTierEquals(itemStackA, itemStackB);
    }

    public static boolean isTopPiece(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() == BackpackItems.backpack_piece && itemStack.getItemDamage() == EPiece.TOP.getDamage()) {
            return true;
        }

        return false;
    }

    public static boolean isMiddlePiece(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() == BackpackItems.backpack_piece && itemStack.getItemDamage() == EPiece.MIDDLE.getDamage()) {
            return true;
        }

        return false;
    }

    public static boolean isBottomPiece(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() == BackpackItems.backpack_piece && itemStack.getItemDamage() == EPiece.BOTTOM.getDamage()) {
            return true;
        }

        return false;
    }

    public static boolean sameTier(ItemStack itemStackA, ItemStack itemStackB) {
        if (itemStackA == null || itemStackB == null) {
            return false;
        }

        if (!TierFrame.itemStackTierEquals(itemStackA, itemStackB)) {
            return false;
        }

        if (!TierLeather.itemStackTierEquals(itemStackA, itemStackB)) {
            return false;
        }

        return true;
    }
}
