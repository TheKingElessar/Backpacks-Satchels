package de.eydamos.backpack.handler;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HandlerClientEvents extends HandlerCommonEvents {
    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        if (!BackpackHelper.isBackpack(event.itemStack)) {
            TierFrame.addTooltip(event.itemStack, event.toolTip);
            TierLeather.addTooltip(event.itemStack, event.toolTip);
        }
    }
}
