package de.eydamos.backpack.proxy;

import de.eydamos.backpack.handler.HandlerClientEvents;
import de.eydamos.backpack.handler.HandlerInputEvents;
import de.eydamos.backpack.util.GeneralUtil;
import de.eydamos.guiadvanced.config.ConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.HashMap;

public class ClientProxy extends CommonProxy {
    private HashMap<String, ItemStack> backpacks = new HashMap<>();

    @Override
    public void registerResourceLoader() {
        IResourceManager resourceManager = Minecraft.getMinecraft().getResourceManager();
        if (resourceManager instanceof IReloadableResourceManager) {
            ((IReloadableResourceManager) resourceManager).registerReloadListener(new ConfigLoader());
        }
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
    public void setBackpackData(String playerUUID, ItemStack backpack) {
        backpacks.put(playerUUID, backpack);
    }

    @Override
    public ItemStack getClientBackpack(String playerUUID) {
        return backpacks.get(playerUUID);
    }

    @Override
    public World getWorldForMapStorage() {
        if (!GeneralUtil.isServerSide()) {
            return Minecraft.getMinecraft().world;
        }

        return super.getWorldForMapStorage();
    }
}
