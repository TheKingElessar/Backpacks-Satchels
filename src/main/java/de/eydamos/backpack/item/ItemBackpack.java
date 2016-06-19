package de.eydamos.backpack.item;

import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.EBackpack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        EBackpack.addSubItem(subItems);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        NBTTagCompound nbtTagCompound = stack.getTagCompound();
        if (nbtTagCompound != null && nbtTagCompound.hasKey("unlocalized")) {
            return "item." + nbtTagCompound.getString("unlocalized");
        }

        return getUnlocalizedName();
    }
}
