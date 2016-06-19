package de.eydamos.backpack.item;

import de.eydamos.backpack.misc.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.TreeMap;

public class ItemFunctionless extends Item {
    public TreeMap<Integer, String> subItems;

    public ItemFunctionless(String unlocalizedName, int maxStackSize, boolean hasSubTypes) {
        setUnlocalizedName(unlocalizedName);
        setMaxStackSize(maxStackSize);
        setHasSubtypes(hasSubTypes);
        setCreativeTab(Constants.tabBackpacks);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (subItems != null) {
            return "item." + subItems.get(stack.getItemDamage());
        }

        return super.getUnlocalizedName(stack);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        if (subItems != null) {
            for (Integer damage : this.subItems.keySet()) {
                subItems.add(new ItemStack(itemIn, 1, damage));
            }
        }
    }

    public void addSubItem(int damage, String unlocalizedName) {
        if (subItems == null) {
            subItems = new TreeMap<Integer, String>();
        }

        subItems.put(damage, unlocalizedName);
    }
}
