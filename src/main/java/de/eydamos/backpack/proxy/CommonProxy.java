package de.eydamos.backpack.proxy;

import de.eydamos.backpack.recipe.ERecipe;

public abstract class CommonProxy implements IProxy {
    @Override
    public void registerHandlers() {
        //Backpack.packetHandler.initialise();

        //EventHandlerBackpack eventHandler = new EventHandlerBackpack();

        //FMLCommonHandler.instance().bus().register(eventHandler);
        //MinecraftForge.EVENT_BUS.register(eventHandler);
    }

    @Override
    public void registerKeybindings() {}

    @Override
    public void addNeiSupport() {}

    @Override
    public void registerRecipes() {
        ERecipe.registerRecipes();
    }
}
