package de.eydamos.backpack.jei;

import de.eydamos.backpack.recipe.AbstractRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

@JEIPlugin
public class BackpackPlugin implements IModPlugin {
    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {

    }

    @Override
    public void register(IModRegistry registry) {
        registry.handleRecipes(AbstractRecipe.class, new RecipeWrapperFactory(), VanillaRecipeCategoryUid.CRAFTING);
    }
}
