package de.eydamos.backpack.proxy;

import de.eydamos.backpack.util.GeneralUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public interface ProxyInterface {
    default void registerResourceLoader() {}

    void registerHandlers();

    default void registerKeybindings() {}

    default void setBackpackData(String playerUUId, ItemStack backpack) {}

    default ItemStack getClientBackpack(String playerUUID)  {
        return ItemStack.EMPTY;
    }

    default ItemStack getClientBackpack(EntityPlayer player) {
        return getClientBackpack(GeneralUtil.getPlayerUUID(player));
    }

    default World getWorldForMapStorage() {
        return DimensionManager.getWorld(0);
    }
}
