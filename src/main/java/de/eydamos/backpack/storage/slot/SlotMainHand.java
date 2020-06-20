package de.eydamos.backpack.storage.slot;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.guiadvanced.inventory.SlotWithState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class SlotMainHand extends SlotWithState
{
    public SlotMainHand(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }
    
    @Override
    public boolean canTakeStack(EntityPlayer playerIn)
    {
        if (BackpackHelper.isBackpack(playerIn.getHeldItemMainhand())) return false;
        else return true;
    }
}
