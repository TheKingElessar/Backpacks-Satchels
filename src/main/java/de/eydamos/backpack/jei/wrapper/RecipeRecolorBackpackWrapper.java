package de.eydamos.backpack.jei.wrapper;

import de.eydamos.backpack.misc.BackpackItems;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeRecolorBackpackWrapper extends BlankRecipeWrapper implements ICraftingRecipeWrapper {
    @Override
    public List getInputs() {
        ArrayList<Object> inputs = new ArrayList<>();

        ArrayList<ItemStack> dyes = new ArrayList<>();
        dyes.add(new ItemStack(Items.DYE, 1, OreDictionary.WILDCARD_VALUE));
        dyes.add(new ItemStack(Items.WATER_BUCKET, 1));

        inputs.add(dyes);
        inputs.add(new ItemStack(BackpackItems.backpack, 1, OreDictionary.WILDCARD_VALUE));

        return inputs;
    }

    @Override
    public List getOutputs() {
        return Collections.singletonList(new ItemStack(BackpackItems.backpack, 1, OreDictionary.WILDCARD_VALUE));
    }
}
