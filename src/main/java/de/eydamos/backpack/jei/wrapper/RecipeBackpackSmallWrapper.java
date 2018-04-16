package de.eydamos.backpack.jei.wrapper;

import de.eydamos.backpack.init.BackpackItems;
import de.eydamos.backpack.item.EBackpack;
import de.eydamos.backpack.item.EFrame;
import de.eydamos.backpack.misc.EItemStack;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;

public class RecipeBackpackSmallWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {
    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 3;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getIngredients(IIngredients ingredients) {
        ArrayList<ItemStack> inputs = new ArrayList<>();

        inputs.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));
        inputs.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));
        inputs.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));
        inputs.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));
        inputs.add(EItemStack.getItemStack(BackpackItems.backpack_frame, 1, EFrame.IRON.getDamage()));
        inputs.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));
        inputs.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));
        inputs.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));
        inputs.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));

        ingredients.setInputs(ItemStack.class, inputs);
        ingredients.setOutput(ItemStack.class, new ItemStack(BackpackItems.backpack, 1, EBackpack.SMALL.getDamage()));
    }
}
