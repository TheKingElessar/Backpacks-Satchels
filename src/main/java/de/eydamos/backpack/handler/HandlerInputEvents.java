package de.eydamos.backpack.handler;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static de.eydamos.backpack.handler.HandlerSatchels.isSatchel;

@SideOnly(value = Side.CLIENT)
public class HandlerInputEvents {
    // 48 represents the B key
    public static KeyBinding personalBackpack = new KeyBinding(
        Localizations.KEY_PERSONAL,
        48,
        Localizations.KEY_CATEGORY
    );

    @SubscribeEvent
    public void handleKeyInput(InputEvent.KeyInputEvent event) {
        if (personalBackpack.isPressed()) {
            EntityPlayerSP player;
            Minecraft mc = FMLClientHandler.instance().getClient();
            if (mc.inGameHasFocus && (player = mc.player) != null) {
                if (player.isSneaking()) {
                    Backpack.packetHandler.sendOpenGui(Constants.Guis.SPECIAL_SLOTS);
                }
                else
                {
                    if (isSatchel(player))
                    {
                        Backpack.packetHandler.sendOpenGui(Constants.Guis.CARRIED_SATCHEL);
                    }
                    
                    else
                    {
                        Backpack.packetHandler.sendOpenGui(Constants.Guis.CARRIED_BACKPACK);
                    }
                }
            }
        }
        
    }
    
}
