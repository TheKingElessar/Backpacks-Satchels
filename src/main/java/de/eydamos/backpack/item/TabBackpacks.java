package de.eydamos.backpack.item;

import de.eydamos.backpack.misc.Localizations;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabBackpacks extends CreativeTabs {
    public TabBackpacks() {
        super(Localizations.TAB_BACKPACKS);
        setBackgroundImageName("item_search.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return EBackpack.SMALL.getItemStack();
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
