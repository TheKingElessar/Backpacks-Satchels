package de.eydamos.backpack.handler;

import de.eydamos.backpack.Backpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.List;

public class HandlerServerEvents extends HandlerCommonEvents {
    @SubscribeEvent
    public void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;

        // let all players now the backpack of the new player
        Backpack.packetHandler.propagateCarriedBackpack(player);

        // let player now the backpacks of all players
        List<EntityPlayerMP> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerList();
        Backpack.packetHandler.sendCarriedBackpacks((EntityPlayerMP) player, players);
    }
}
