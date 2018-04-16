package de.eydamos.backpack.jei.handler;

import de.eydamos.backpack.jei.wrapper.RecipeBackpackMediumWrapper;
import de.eydamos.backpack.recipe.RecipeBackpackMedium;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

public class RecipeBackpackMediumHandler implements IRecipeHandler<RecipeBackpackMedium> {
    @Nonnull
    @Override
    public Class<RecipeBackpackMedium> getRecipeClass() {
        return RecipeBackpackMedium.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull RecipeBackpackMedium recipe) {
        return VanillaRecipeCategoryUid.CRAFTING;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeBackpackMedium recipe) {
        return new RecipeBackpackMediumWrapper();
    }

    @Override
    public boolean isRecipeValid(@Nonnull RecipeBackpackMedium recipe) {
        return recipe.getRecipeOutput() != null;
    }
}
