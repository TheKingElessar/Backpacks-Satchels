package de.eydamos.backpack.item;

import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Localizations;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabBackpacks extends CreativeTabs {

    public TabBackpacks() {
        super(CreativeTabs.creativeTabArray.length, Localizations.TAB_BACKPACKS);
    }

    @Override
    public Item getTabIconItem() {
        return BackpackItems.backpack;
    }
}
