package de.eydamos.backpack.item;

import de.eydamos.backpack.helper.HelperNBTData;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subItems) {
        for (Integer damage : this.subItems.keySet()) {
            subItems.add(new ItemStack(item, 1, damage));
        }
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        HelperNBTData.addTooltip(itemStack, tooltip);
    }

    public void addSubItem(int damage, String unlocalizedName) {
        if (subItems == null) {
            subItems = new TreeMap<Integer, String>();
        }

        subItems.put(damage, unlocalizedName);
    }
}
