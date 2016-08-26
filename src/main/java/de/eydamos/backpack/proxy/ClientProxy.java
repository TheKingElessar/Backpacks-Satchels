package de.eydamos.backpack.proxy;

import de.eydamos.backpack.handler.HandlerClientEvents;
import de.eydamos.backpack.handler.HandlerInputEvents;
import de.eydamos.backpack.misc.Bootstrap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    private ItemStack backpack;

    @Override
    public void registerItems() {
        super.registerItems();
        Bootstrap.registerVariants();
    }

    @Override
    public void registerIcons() {
        Bootstrap.registerIcons();
    }

    @Override
    public void registerHandlers() {
        super.registerHandlers();

        MinecraftForge.EVENT_BUS.register(new HandlerInputEvents());
        MinecraftForge.EVENT_BUS.register(new HandlerClientEvents());
    }

    @Override
    public void registerKeybindings() {
        ClientRegistry.registerKeyBinding(HandlerInputEvents.personalBackpack);
    }

    @Override
    public void setClientBackpack(ItemStack backpack) {
        this.backpack = backpack;
    }

    @Override
    public ItemStack getClientBackpack() {
        return backpack;
    }
}
