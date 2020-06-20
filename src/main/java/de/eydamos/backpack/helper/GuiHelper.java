package de.eydamos.backpack.helper;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHelper {
    @SideOnly(Side.CLIENT)
    public static void displayRenameGui(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.RENAME_BACKPACK, player.world, 0, 0, 0);
    }

    public static void displayBackpack(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.BACKPACK, player.world, 0, 0, 0);
    }

    public static void displaySpecialSlots(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.SPECIAL_SLOTS, player.world, 0, 0, 0);
    }

    public static void displayCarriedBackpack(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.CARRIED_BACKPACK, player.world, 0, 0, 0);
    }
    
    public static void displayCarriedSatchel(EntityPlayer player) {
        player.openGui(Backpack.instance, Constants.Guis.CARRIED_SATCHEL, player.world, 0, 0, 0);
    }
}
