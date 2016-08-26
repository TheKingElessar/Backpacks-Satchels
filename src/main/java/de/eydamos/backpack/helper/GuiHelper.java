package de.eydamos.backpack.helper;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.network.message.MessageOpenGui;
import de.eydamos.backpack.network.message.MessageRenameBackpack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHelper {
    @SideOnly(Side.CLIENT)
    public static void displayRenameGui(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.RENAME_BACKPACK, player.worldObj, 0, 0, 0);
    }

    @SideOnly(Side.CLIENT)
    public static void renameBackpack(String name) {
        MessageRenameBackpack message = new MessageRenameBackpack(name);
        // send new name to server
        Backpack.packetHandler.networkWrapper.sendToServer(message);
        // save the name on client
        message.setName(Minecraft.getMinecraft().thePlayer, name);
    }

    public static void displayBackpack(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.BACKPACK, player.worldObj, 0, 0, 0);
    }

    public static void displaySpecialSlots(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.SPECIAL_SLOTS, player.worldObj, 0, 0, 0);
    }

    public static void displayCarriedBackpack(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.CARRIED_BACKPACK, player.worldObj, 0, 0, 0);
    }

    @SideOnly(Side.CLIENT)
    public static void sendOpenGui(byte gui) {
        MessageOpenGui message = new MessageOpenGui(gui);
        Backpack.packetHandler.networkWrapper.sendToServer(message);
    }
}
