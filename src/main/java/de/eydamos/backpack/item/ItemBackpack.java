package de.eydamos.backpack.item;

import de.eydamos.backpack.misc.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBackpack extends Item {
    public ItemBackpack() {
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(Constants.tabBackpacks);
        setUnlocalizedName(Constants.DOMAIN);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subItems) {
        for (EBackpack backpack : EBackpack.values()) {
            subItems.add(new ItemStack(item, 1, backpack.getDamage()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        String name = super.getUnlocalizedName(itemStack);

        name += '_' + EBackpack.getIdentifierByDamage(itemStack.getItemDamage());

        return name;
    }
}
