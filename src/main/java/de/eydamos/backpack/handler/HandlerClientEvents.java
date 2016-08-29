package de.eydamos.backpack.handler;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.model.LayerBackpack;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
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
    private static HashSet<RenderLivingBase> seenPlayers = new HashSet<>();
    private static LinkedList<RenderLivingBase> toInit = new LinkedList<>();

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        if (!BackpackHelper.isBackpack(event.getItemStack())) {
            TierFrame.addTooltip(event.getItemStack(), event.getToolTip());
            TierLeather.addTooltip(event.getItemStack(), event.getToolTip());
        }
    }

    @SubscribeEvent
    public void renderStart(RenderLivingEvent.Pre event) {
        if (!seenPlayers.contains(event.getRenderer()) && event.getRenderer() instanceof RenderPlayer) {
            seenPlayers.add(event.getRenderer());
            toInit.add(event.getRenderer());
        }
    }

    @SubscribeEvent
    public void layers(TickEvent.RenderTickEvent event) {
        for (RenderLivingBase<?> renderer : toInit) {
            renderer.addLayer(new LayerBackpack((RenderPlayer) renderer));
        }
        toInit.clear();
    }

    @SubscribeEvent
    public void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Backpack.packetHandler.propagateCarriedBackpack(event.player);
    }
}
