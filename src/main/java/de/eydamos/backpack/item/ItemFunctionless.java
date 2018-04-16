package de.eydamos.backpack.item;

import de.eydamos.backpack.Features;
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
                    if (stick.isEnabled()) {
                        subItems.add(EItemStack.getItemStack(BackpackItems.stick, 1, stick.getDamage()));
                    }
                }
                break;
            case "item.backpack_frame":
                for (EFrame frame : EFrame.values()) {
                    if (Features.BACKPACK_FRAME.isEnabled() && frame.isEnabled()) {
                        subItems.add(EItemStack.getItemStack(BackpackItems.backpack_frame, 1, frame.getDamage()));
                    }
                }
                break;
            case "item.backpack_piece":
                for (EPiece piece : EPiece.values()) {
                    if (Features.BACKPACK_PIECE.isEnabled()) {
                        subItems.add(EItemStack.getItemStack(BackpackItems.backpack_piece, 1, piece.getDamage()));
                    }
                }
                break;
            case "item.bound_leather":
                if (Features.LEATHER_BOUND.isEnabled()) {
                    subItems.add(EItemStack.BOUND_LEATHER.getItemStack(1));
                }
                break;
            case "item.tanned_leather":
                if (Features.LEATHER_TANNED.isEnabled()) {
                    subItems.add(EItemStack.TANNED_LEATHER.getItemStack(1));
                }
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
