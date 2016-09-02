package de.eydamos.backpack.jei.wrapper;

import de.eydamos.backpack.item.EFrame;
import de.eydamos.backpack.item.EPiece;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.EItem;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeBackpackPieceBottomWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {
    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Nonnull
    @Override
    public List getInputs() {
        ArrayList<Object> inputs = new ArrayList<>();

        ArrayList<ItemStack> leather = new ArrayList<>();
        leather.add(new ItemStack(Items.rabbit_hide, 1, 0));
        leather.add(new ItemStack(Items.leather, 1, 0));
        leather.add(EItem.getItemStack(BackpackItems.tanned_leather, 1, 0));

        // middle row
        inputs.add(leather);
        inputs.add(EItem.getItemStack(BackpackItems.backpack_frame, 1, OreDictionary.WILDCARD_VALUE));
        inputs.add(leather);
        // bottom row
        inputs.add(leather);
        inputs.add(leather);
        inputs.add(leather);

        return inputs;
    }

    @Nonnull
    @Override
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(EItem.getItemStack(BackpackItems.backpack_piece, 1, EPiece.BOTTOM.getDamage()));
    }
}
