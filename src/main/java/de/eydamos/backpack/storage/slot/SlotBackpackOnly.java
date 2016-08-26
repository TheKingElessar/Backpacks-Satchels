package de.eydamos.backpack.storage.slot;

import de.eydamos.backpack.helper.BackpackHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBackpackOnly extends Slot {
    public SlotBackpackOnly(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    /**
     * Check if the stack is a valid item for this slot. Is only true if item is a backpack.
     */
    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return BackpackHelper.isBackpack(itemStack);
    }
}
