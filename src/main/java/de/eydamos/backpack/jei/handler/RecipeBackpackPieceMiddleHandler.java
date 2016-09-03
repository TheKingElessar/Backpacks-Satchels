package de.eydamos.backpack.jei.handler;

import de.eydamos.backpack.jei.wrapper.RecipeBackpackPieceMiddleWrapper;
import de.eydamos.backpack.recipe.RecipeBackpackPieceMiddle;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

public class RecipeBackpackPieceMiddleHandler implements IRecipeHandler<RecipeBackpackPieceMiddle> {
    @Nonnull
    @Override
    public Class<RecipeBackpackPieceMiddle> getRecipeClass() {
        return RecipeBackpackPieceMiddle.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return VanillaRecipeCategoryUid.CRAFTING;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeBackpackPieceMiddle recipe) {
        return new RecipeBackpackPieceMiddleWrapper();
    }

    @Override
    public boolean isRecipeValid(@Nonnull RecipeBackpackPieceMiddle recipe) {
        return recipe.getRecipeOutput() != null;
    }
}
