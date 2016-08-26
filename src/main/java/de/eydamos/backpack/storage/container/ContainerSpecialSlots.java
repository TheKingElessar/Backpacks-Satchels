package de.eydamos.backpack.storage.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class ContainerSpecialSlots extends ContainerAdvanced {
    public ContainerSpecialSlots(IInventory inventoryToSave, EntityPlayer player) {
        super(inventoryToSave, player);
    }
}
