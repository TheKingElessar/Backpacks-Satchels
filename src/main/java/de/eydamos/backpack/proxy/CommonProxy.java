package de.eydamos.backpack.proxy;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.handler.HandlerGui;
import de.eydamos.backpack.misc.Bootstrap;
import de.eydamos.backpack.recipe.ERecipe;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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

        NetworkRegistry.INSTANCE.registerGuiHandler(Backpack.instance, new HandlerGui());
    }

    @Override
    public void registerKeybindings() {}

    @Override
    public void registerRecipes() {
        ERecipe.registerRecipes();
    }
}
