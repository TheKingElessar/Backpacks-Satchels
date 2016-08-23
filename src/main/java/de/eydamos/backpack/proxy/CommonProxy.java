package de.eydamos.backpack.proxy;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.misc.Bootstrap;
import de.eydamos.backpack.recipe.ERecipe;

public abstract class CommonProxy implements IProxy {
    @Override
    public void registerItems() {
        Bootstrap.register();
    }

    @Override
    public void registerIcons() {}

    @Override
    public void registerHandlers() {
        Backpack.packetHandler.initialise();
    }

    @Override
    public void registerKeybindings() {}

    @Override
    public void registerRecipes() {
        ERecipe.registerRecipes();
    }
}
