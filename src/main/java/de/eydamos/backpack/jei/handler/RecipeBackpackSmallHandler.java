package de.eydamos.backpack.jei.handler;

import de.eydamos.backpack.jei.wrapper.RecipeBackpackSmallWrapper;
import de.eydamos.backpack.recipe.RecipeBackpackSmall;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

public class RecipeBackpackSmallHandler implements IRecipeHandler<RecipeBackpackSmall> {
    @Nonnull
    @Override
    public Class<RecipeBackpackSmall> getRecipeClass() {
        return RecipeBackpackSmall.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull RecipeBackpackSmall recipe) {
        return VanillaRecipeCategoryUid.CRAFTING;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeBackpackSmall recipe) {
        return new RecipeBackpackSmallWrapper();
    }

    @Override
    public boolean isRecipeValid(@Nonnull RecipeBackpackSmall recipe) {
        return recipe.getRecipeOutput() != null;
    }
}
