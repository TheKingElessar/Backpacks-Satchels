package de.eydamos.backpack.item;

import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.EItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ItemFunctionless extends Item {
    public ItemFunctionless(String unlocalizedName, int maxStackSize, boolean hasSubTypes) {
        setRegistryName(unlocalizedName);
        setUnlocalizedName(unlocalizedName);
        setMaxStackSize(maxStackSize);
        setHasSubtypes(hasSubTypes);
        setCreativeTab(Constants.tabBackpacks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @ParametersAreNonnullByDefault
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        if (item == BackpackItems.stick) {
            for (EStick stick : EStick.values()) {
                subItems.add(EItem.getItemStack(item, 1, stick.getDamage()));
            }
        } else if (item == BackpackItems.backpack_frame) {
            for (EFrame frame : EFrame.values()) {
                subItems.add(EItem.getItemStack(item, 1, frame.getDamage()));
            }
        } else if (item == BackpackItems.backpack_piece) {
            for (EPiece piece : EPiece.values()) {
                subItems.add(EItem.getItemStack(item, 1, piece.getDamage()));
            }
        } else {
            subItems.add(EItem.getItemStack(item, 1, 0));
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
}
