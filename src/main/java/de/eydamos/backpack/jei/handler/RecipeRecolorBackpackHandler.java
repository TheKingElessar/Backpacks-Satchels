package de.eydamos.backpack.jei.handler;

import de.eydamos.backpack.jei.wrapper.RecipeRecolorBackpackWrapper;
import de.eydamos.backpack.recipe.RecipeRecolorBackpack;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

public class RecipeRecolorBackpackHandler implements IRecipeHandler<RecipeRecolorBackpack> {
    @Nonnull
    @Override
    public Class<RecipeRecolorBackpack> getRecipeClass() {
        return RecipeRecolorBackpack.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull RecipeRecolorBackpack recipe) {
        return VanillaRecipeCategoryUid.CRAFTING;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeRecolorBackpack recipe) {
        return new RecipeRecolorBackpackWrapper();
    }

    @Override
    public boolean isRecipeValid(@Nonnull RecipeRecolorBackpack recipe) {
        return recipe.getRecipeOutput() != null;
    }
}
