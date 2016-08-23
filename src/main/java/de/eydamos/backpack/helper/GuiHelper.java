package de.eydamos.backpack.helper;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.gui.GuiBackpackRename;
import de.eydamos.backpack.network.message.MessageRenameBackpack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHelper {
    @SideOnly(Side.CLIENT)
    public static void displayRenameGui() {
        Minecraft.getMinecraft().displayGuiScreen(new GuiBackpackRename());
    }

    @SideOnly(Side.CLIENT)
    public static void renameBackpack(String name) {
        MessageRenameBackpack message = new MessageRenameBackpack(name);
        // send new name to server
        Backpack.packetHandler.networkWrapper.sendToServer(message);
        // save the name on client
        message.setName(Minecraft.getMinecraft().thePlayer, name);
    }
}
