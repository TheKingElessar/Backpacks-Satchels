package de.eydamos.backpack.handler;

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
            if (mc.inGameHasFocus && (player = mc.thePlayer) != null) {
                if (player.isSneaking()) {
                    // TODO
                    //GuiHelper.sendOpenPersonalGui(Constants.Guis.OPEN_PERSONAL_SLOT);
                } else {
                    // TODO
                    //GuiHelper.sendOpenPersonalGui(Constants.Guis.OPEN_PERSONAL_BACKPACK);
                }
            }
        }
    }
}
