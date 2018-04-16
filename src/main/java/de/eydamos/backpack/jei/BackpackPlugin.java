package de.eydamos.backpack.jei;

import de.eydamos.backpack.jei.handler.*;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

import javax.annotation.Nonnull;

@JEIPlugin
public class BackpackPlugin extends BlankModPlugin {
    @Override
    public void register(@Nonnull IModRegistry registry) {
        registry.addRecipeHandlers(
            new RecipeBackpackSmallHandler(),
            new RecipeBackpackMediumHandler(),
            new RecipeBackpackBigHandler(),
            new RecipeBackpackPieceTopHandler(),
            new RecipeBackpackPieceMiddleHandler(),
            new RecipeBackpackPieceBottomHandler(),
            new RecipeRecolorBackpackHandler()
        );
    }
}
