package de.eydamos.backpack.jei.handler;

import de.eydamos.backpack.jei.wrapper.RecipeBackpackBigWrapper;
import de.eydamos.backpack.recipe.RecipeBackpackBig;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

public class RecipeBackpackBigHandler implements IRecipeHandler<RecipeBackpackBig> {
    @Nonnull
    @Override
    public Class<RecipeBackpackBig> getRecipeClass() {
        return RecipeBackpackBig.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return VanillaRecipeCategoryUid.CRAFTING;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull RecipeBackpackBig recipe) {
        return getRecipeCategoryUid();
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeBackpackBig recipe) {
        return new RecipeBackpackBigWrapper();
    }

    @Override
    public boolean isRecipeValid(@Nonnull RecipeBackpackBig recipe) {
        return recipe.getRecipeOutput() != null;
    }
}
