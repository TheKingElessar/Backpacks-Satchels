package de.eydamos.backpack.storage.slot;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.guiadvanced.inventory.SlotWithState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotBackpack extends SlotWithState {
    public SlotBackpack(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return !BackpackHelper.isBackpack(itemStack);
    }
}
