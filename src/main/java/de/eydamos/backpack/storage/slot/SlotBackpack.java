package de.eydamos.backpack.storage.slot;

import de.eydamos.backpack.helper.BackpackHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBackpack extends Slot {
    public SlotBackpack(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return !BackpackHelper.isBackpack(itemStack);
    }
}
