package de.eydamos.backpack.jei.handler;

import de.eydamos.backpack.jei.wrapper.RecipeBackpackPieceTopWrapper;
import de.eydamos.backpack.recipe.RecipeBackpackPieceTop;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

public class RecipeBackpackPieceTopHandler implements IRecipeHandler<RecipeBackpackPieceTop> {
    @Nonnull
    @Override
    public Class<RecipeBackpackPieceTop> getRecipeClass() {
        return RecipeBackpackPieceTop.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return VanillaRecipeCategoryUid.CRAFTING;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeBackpackPieceTop recipe) {
        return new RecipeBackpackPieceTopWrapper();
    }

    @Override
    public boolean isRecipeValid(@Nonnull RecipeBackpackPieceTop recipe) {
        return recipe.getRecipeOutput() != null;
    }
}
