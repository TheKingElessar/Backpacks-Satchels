package de.eydamos.guiadvanced.inventory;

import net.minecraft.inventory.IInventory;

public class SlotWithState extends net.minecraft.inventory.Slot {
    private boolean enabled = true;

    public SlotWithState(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean value) {
        enabled = value;
    }
}
