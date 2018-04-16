package de.eydamos.backpack.item;

import de.eydamos.backpack.init.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.EItemStack;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemFunctionless extends Item {
    public ItemFunctionless(String unlocalizedName) {
        this(unlocalizedName, 64, false);
    }

    public ItemFunctionless(String unlocalizedName, int maxStackSize) {
        this(unlocalizedName, maxStackSize, false);
    }

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
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (!isInCreativeTab(tab)) {
            return;
        }

        switch (getUnlocalizedName()) {
            case "item.stick":
                for (EStick stick : EStick.values()) {
                    subItems.add(EItemStack.getItemStack(BackpackItems.stick, 1, stick.getDamage()));
                }
                break;
            case "item.backpack_frame":
                for (EFrame frame : EFrame.values()) {
                    subItems.add(EItemStack.getItemStack(BackpackItems.backpack_frame, 1, frame.getDamage()));
                }
                break;
            case "item.backpack_piece":
                for (EPiece piece : EPiece.values()) {
                    subItems.add(EItemStack.getItemStack(BackpackItems.backpack_piece, 1, piece.getDamage()));
                }
                break;
            case "item.bound_leather":
                subItems.add(EItemStack.BOUND_LEATHER.getItemStack(1));
                break;
            case "item.tanned_leather":
                subItems.add(EItemStack.TANNED_LEATHER.getItemStack(1));
                break;
        }
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getUnlocalizedName(ItemStack itemStack) {
        String name = super.getUnlocalizedName(itemStack);

        switch (name) {
            case "item.stick":
                name += "_" + EStick.getIdentifierByDamage(itemStack.getItemDamage());
                break;
            case "item.backpack_frame":
                name += '_' + EFrame.getIdentifierByDamage(itemStack.getItemDamage());
                break;
            case "item.backpack_piece":
                name += '_' + EPiece.getIdentifierByDamage(itemStack.getItemDamage());
                break;
        }

        return name;
    }
}
