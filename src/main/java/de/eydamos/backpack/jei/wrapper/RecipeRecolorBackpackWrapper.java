package de.eydamos.backpack.jei.wrapper;

import de.eydamos.backpack.init.BackpackItems;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;

public class RecipeRecolorBackpackWrapper extends BlankRecipeWrapper implements IRecipeWrapper {
    @Override
    @ParametersAreNonnullByDefault
    public void getIngredients(IIngredients ingredients) {
        ArrayList<ItemStack> dyes = new ArrayList<>();
        dyes.add(new ItemStack(Items.DYE, 1, OreDictionary.WILDCARD_VALUE));
        dyes.add(new ItemStack(Items.WATER_BUCKET, 1));

        ingredients.setInputs(ItemStack.class, dyes);
        ingredients.setInput(ItemStack.class, new ItemStack(BackpackItems.backpack, 1, OreDictionary.WILDCARD_VALUE));

        ingredients.setOutput(ItemStack.class, new ItemStack(BackpackItems.backpack, 1, OreDictionary.WILDCARD_VALUE));
    }
}
