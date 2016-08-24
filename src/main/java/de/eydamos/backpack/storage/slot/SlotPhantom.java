package de.eydamos.backpack.storage.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotPhantom extends Slot {
    public SlotPhantom(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    /**
     * If the stack in this slot can be changed.
     *
     * @return True if the slot can be changed, false otherwise.
     */
    public boolean canChangeStack() {
        return true;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return false;
    }
}
