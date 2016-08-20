package de.eydamos.backpack.misc;

import de.eydamos.backpack.helper.HelperNBTData;
import de.eydamos.backpack.item.EFrame;
import de.eydamos.backpack.item.EPiece;
import de.eydamos.backpack.item.EStick;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public enum EItem {
    BOUND_LEATHER(BackpackItems.bound_leather, 0),
    TANNED_LEATHER(BackpackItems.tanned_leather, 0),
    STICK_STONE(BackpackItems.stick, EStick.STONE.getDamage()),
    STICK_IRON(BackpackItems.stick, EStick.IRON.getDamage()),
    FRAME_WOOD(BackpackItems.backpack_frame, EFrame.WOOD.getDamage()),
    FRAME_STONE(BackpackItems.backpack_frame, EFrame.STONE.getDamage()),
    FRAME_IRON(BackpackItems.backpack_frame, EFrame.IRON.getDamage()),
    BACKPACK_PICE_TOP(BackpackItems.backpack_piece, EPiece.TOP.getDamage()),
    BACKPACK_PICE_MIDDLE(BackpackItems.backpack_piece, EPiece.MIDDLE.getDamage()),
    BACKPACK_PICE_BOTTOM(BackpackItems.backpack_piece, EPiece.BOTTOM.getDamage());

    protected Item item;
    protected int damage;
    protected NBTTagCompound nbtTagCompound;

    EItem(Item item, int damage, Object... nbtData) {
        this.item = item;
        this.damage = damage;
        this.nbtTagCompound = new NBTTagCompound();

        for (int i = 0; i < nbtData.length; i += 2) {
            String key = String.valueOf(nbtData[i]);
            String value = String.valueOf(nbtData[i + 1]);

            nbtTagCompound.setString(key, value);
        }
    }

    public ItemStack getItemStack(int amount) {
        ItemStack itemStack = new ItemStack(item, amount, damage);

        HelperNBTData.setFrameTier(itemStack, itemStack);
        HelperNBTData.setLeatherTier(itemStack, itemStack);

        if (!nbtTagCompound.hasNoTags()) {
            itemStack.setTagCompound(nbtTagCompound);
        }

        return itemStack;
    }
}
