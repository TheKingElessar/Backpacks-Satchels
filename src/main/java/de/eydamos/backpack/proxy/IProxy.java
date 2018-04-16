package de.eydamos.backpack.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IProxy {
    public void registerHandlers();

    public void registerKeybindings();

    public void setBackpackData(String playerUUId, ItemStack backpack);

    public ItemStack getClientBackpack(String playerUUID);

    public ItemStack getClientBackpack(EntityPlayer player);
}
