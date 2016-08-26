package de.eydamos.backpack.proxy;

import net.minecraft.item.ItemStack;

public interface IProxy {
    public void registerItems();

    public void registerIcons();

    public void registerHandlers();

    public void registerKeybindings();

    public void registerRecipes();

    public void setClientBackpack(ItemStack backpack);

    public ItemStack getClientBackpack();
}
