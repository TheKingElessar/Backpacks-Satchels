package de.eydamos.backpack.handler;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.model.LayerBackpack;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.LinkedList;

@SideOnly(Side.CLIENT)
public class HandlerClientEvents extends HandlerCommonEvents {
    private static HashSet<RendererLivingEntity> seenPlayers = new HashSet<>();
    private static LinkedList<RendererLivingEntity> toInit = new LinkedList<>();

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        if (!BackpackHelper.isBackpack(event.itemStack)) {
            TierFrame.addTooltip(event.itemStack, event.toolTip);
            TierLeather.addTooltip(event.itemStack, event.toolTip);
        }
    }

    @SubscribeEvent
    public void renderStart(RenderLivingEvent.Pre event) {
        if (!seenPlayers.contains(event.renderer) && event.renderer instanceof RenderPlayer) {
            seenPlayers.add(event.renderer);
            toInit.add(event.renderer);
        }
    }

    @SubscribeEvent
    public void layers(TickEvent.RenderTickEvent event) {
        for (RendererLivingEntity<?> renderer : toInit) {
            renderer.addLayer(new LayerBackpack((RenderPlayer) renderer));
        }
        toInit.clear();
    }

    @SubscribeEvent
    public void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Backpack.packetHandler.propagateCarriedBackpack(event.player);
    }
}
