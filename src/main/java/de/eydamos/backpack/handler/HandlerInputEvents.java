package de.eydamos.backpack.handler;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value = Side.CLIENT)
public class HandlerInputEvents {
    // 48 represents the B key
    public static KeyBinding personalBackpack = new KeyBinding("key.personalBackpack", 48, "key.categories.backpack");

    @SubscribeEvent
    public void handleKeyInput(InputEvent.KeyInputEvent event) {
        if (personalBackpack.isPressed()) {
            EntityPlayerSP player;
            Minecraft mc = FMLClientHandler.instance().getClient();
            if (mc.inGameHasFocus && (player = mc.player) != null) {
                if (player.isSneaking()) {
                    Backpack.packetHandler.sendOpenGui(Constants.Guis.SPECIAL_SLOTS);
                } else {
                    Backpack.packetHandler.sendOpenGui(Constants.Guis.CARRIED_BACKPACK);
                }
            }
        }
    }
}
