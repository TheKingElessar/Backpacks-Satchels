package de.eydamos.backpack.handler;

import cofh.core.util.helpers.ChatHelper;
import cofh.core.util.helpers.SecurityHelper;
import cofh.core.util.helpers.ServerHelper;
import cofh.thermalexpansion.init.TEItems;
import de.eydamos.backpack.helper.GuiHelper;
import de.eydamos.backpack.storage.container.ContainerAdvanced;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import static cofh.core.item.ItemMulti.canPlayerAccess;
import static cofh.thermalexpansion.item.ItemSatchel.needsTag;
import static cofh.thermalexpansion.item.ItemSatchel.setDefaultInventoryTag;
import static de.eydamos.backpack.factory.FactorySpecialSlots.getContainer;

public class HandlerSatchels
{
    public static boolean isSatchel(EntityPlayer player)
    {
        ContainerAdvanced container = getContainer(player);
        
        ItemStack itemStack = container.inventorySlots.get(0).getStack();
        if (itemStack.getItem() == TEItems.itemSatchel)
        {
            return true;
        }
        
        return false;
        
    }
    
    public static ItemStack getSatchel(EntityPlayer player)
    {
        if (isSatchel(player))
        {
            ContainerAdvanced container = getContainer(player);
            
            return container.inventorySlots.get(0).getStack();
        }
        else
        {
            return null;
        }
    }
    
    public static void openSatchel(World world, EntityPlayer player)
    {
        if (world == null)
        {
            world = player.getEntityWorld();
        }
        
        ItemStack stack = getSatchel(player);
        
        if (needsTag(stack))
        {
            setDefaultInventoryTag(stack);
        }
        
        if (ServerHelper.isServerWorld(world))
        {
            if (SecurityHelper.isSecure(stack) && SecurityHelper.isDefaultUUID(SecurityHelper.getOwner(stack).getId()))
            {
                SecurityHelper.setOwner(stack, player.getGameProfile());
                ChatHelper.sendIndexedChatMessageToPlayer(player, new TextComponentTranslation("chat.cofh.secure.item.success"));
            }
            if (canPlayerAccess(stack, player))
            {
                GuiHelper.displayCarriedSatchel(player);
                // Todo: Flashes satchel inventory for a second then closes.
                
                // Todo: On initial world load, when opening it it opens an empty, uninitiLized brown backpack. Fixed when you open the special slots.
                
                // Todo: Save data? Probably related to above problem.
            }
            else if (SecurityHelper.isSecure(stack))
            {
                ChatHelper.sendIndexedChatMessageToPlayer(player, new TextComponentTranslation("chat.cofh.secure.warning", SecurityHelper.getOwnerName(stack)));
            }
        }
    }
    
}
