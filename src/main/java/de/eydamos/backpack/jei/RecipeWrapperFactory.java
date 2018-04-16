package de.eydamos.backpack.jei;

import de.eydamos.backpack.jei.wrapper.*;
import de.eydamos.backpack.recipe.*;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

import javax.annotation.ParametersAreNonnullByDefault;

public class RecipeWrapperFactory implements IRecipeWrapperFactory<AbstractRecipe> {
    public RecipeWrapperFactory() {
    }

    @Override
    @ParametersAreNonnullByDefault
    public IRecipeWrapper getRecipeWrapper(AbstractRecipe abstractRecipe) {
        if (abstractRecipe instanceof RecipeBackpackBig) {
            return new RecipeBackpackBigWrapper();
        } else if (abstractRecipe instanceof RecipeBackpackMedium) {
            return new RecipeBackpackMediumWrapper();
        } else if (abstractRecipe instanceof RecipeBackpackSmall) {
            return new RecipeBackpackSmallWrapper();
        } else if (abstractRecipe instanceof RecipeBackpackPieceTop) {
            return new RecipeBackpackPieceTopWrapper();
        } else if (abstractRecipe instanceof RecipeBackpackPieceMiddle) {
            return new RecipeBackpackPieceMiddleWrapper();
        } else if (abstractRecipe instanceof RecipeBackpackPieceBottom) {
            return new RecipeBackpackPieceBottomWrapper();
        } else if (abstractRecipe instanceof RecipeRecolorBackpack) {
            return new RecipeRecolorBackpackWrapper();
        }

        return null;
    }
}
