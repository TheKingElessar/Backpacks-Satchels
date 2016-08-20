package de.eydamos.backpack.item;

import de.eydamos.backpack.helper.HelperNBTData;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFunctionless extends Item {
    public ItemFunctionless(String unlocalizedName, int maxStackSize, boolean hasSubTypes) {
        setUnlocalizedName(unlocalizedName);
        setMaxStackSize(maxStackSize);
        setHasSubtypes(hasSubTypes);
        setCreativeTab(Constants.tabBackpacks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subItems) {
        if (item == BackpackItems.stick) {
            for (EStick stick : EStick.values()) {
                subItems.add(new ItemStack(item, 1, stick.getDamage()));
            }
        } else if(item == BackpackItems.backpack_frame) {
            for (EFrame frame : EFrame.values()) {
                ItemStack itemStack = new ItemStack(item, 1, frame.getDamage());
                HelperNBTData.setFrameTier(itemStack, itemStack);
                subItems.add(itemStack);
            }
        } else if (item == BackpackItems.backpack_piece) {
            for (EPiece piece : EPiece.values()) {
                subItems.add(new ItemStack(item, 1, piece.getDamage()));
            }
        } else {
            ItemStack itemStack = new ItemStack(item, 1);
            HelperNBTData.setFrameTier(itemStack, itemStack);
            HelperNBTData.setLeatherTier(itemStack, itemStack);
            subItems.add(itemStack);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        String name = super.getUnlocalizedName(itemStack);

        if (itemStack.getItem() == BackpackItems.stick) {
            name += "_" + EStick.getIdentifierByDamage(itemStack.getItemDamage());
        } else if (itemStack.getItem() == BackpackItems.backpack_frame) {
            name += '_' + EFrame.getIdentifierByDamage(itemStack.getItemDamage());
        } else if (itemStack.getItem() == BackpackItems.backpack_piece) {
            name += '_' + EPiece.getIdentifierByDamage(itemStack.getItemDamage());
        }

        return name;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        HelperNBTData.addTooltip(itemStack, tooltip);
    }
}
