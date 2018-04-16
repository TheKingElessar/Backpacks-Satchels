package de.eydamos.backpack.proxy;

import de.eydamos.backpack.handler.HandlerClientEvents;
import de.eydamos.backpack.handler.HandlerInputEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.HashMap;

public class ClientProxy extends CommonProxy {
    private HashMap<String, ItemStack> backpacks = new HashMap<>();

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
    public void setBackpackData(String playerUUID, ItemStack backpack) {
        backpacks.put(playerUUID, backpack);
    }

    @Override
    public ItemStack getClientBackpack(String playerUUID) {
        return backpacks.get(playerUUID);
    }
}
