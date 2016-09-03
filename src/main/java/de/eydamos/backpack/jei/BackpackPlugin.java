package de.eydamos.backpack.jei;

import de.eydamos.backpack.jei.handler.*;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
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

        IJeiHelpers jeiHelper = registry.getJeiHelpers();

        jeiHelper.getNbtIgnoreList().ignoreNbtTagNames(BackpackItems.backpack_piece, Constants.NBT.FRAME_TIER);
        jeiHelper.getNbtIgnoreList().ignoreNbtTagNames(BackpackItems.backpack_piece, Constants.NBT.LEATHER_TIER);
    }
}
