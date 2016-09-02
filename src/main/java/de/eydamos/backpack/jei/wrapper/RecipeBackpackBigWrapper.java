package de.eydamos.backpack.jei.wrapper;

import de.eydamos.backpack.item.EBackpack;
import de.eydamos.backpack.item.EPiece;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.EItem;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeBackpackBigWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {
    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 3;
    }

    @Nonnull
    @Override
    public List getInputs() {
        ArrayList<ItemStack> inputs = new ArrayList<>();

        inputs.add(EItem.getItemStack(BackpackItems.backpack_piece, 1, EPiece.TOP.getDamage()));
        inputs.add(EItem.getItemStack(BackpackItems.backpack_piece, 1, EPiece.MIDDLE.getDamage()));
        inputs.add(EItem.getItemStack(BackpackItems.backpack_piece, 1, EPiece.BOTTOM.getDamage()));

        return inputs;
    }

    @Nonnull
    @Override
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(new ItemStack(BackpackItems.backpack, 1, EBackpack.BIG.getDamage()));
    }
}
