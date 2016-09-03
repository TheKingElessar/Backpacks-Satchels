package de.eydamos.backpack.jei.handler;

import de.eydamos.backpack.jei.wrapper.RecipeBackpackPieceBottomWrapper;
import de.eydamos.backpack.recipe.RecipeBackpackPieceBottom;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

public class RecipeBackpackPieceBottomHandler implements IRecipeHandler<RecipeBackpackPieceBottom> {
    @Nonnull
    @Override
    public Class<RecipeBackpackPieceBottom> getRecipeClass() {
        return RecipeBackpackPieceBottom.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return VanillaRecipeCategoryUid.CRAFTING;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeBackpackPieceBottom recipe) {
        return new RecipeBackpackPieceBottomWrapper();
    }

    @Override
    public boolean isRecipeValid(@Nonnull RecipeBackpackPieceBottom recipe) {
        return recipe.getRecipeOutput() != null;
    }
}
