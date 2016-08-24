package de.eydamos.backpack.handler;

import de.eydamos.backpack.factory.FactoryBackpack;
import de.eydamos.backpack.gui.GuiBackpackRename;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class HandlerGui implements IGuiHandler {
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch(id) {
            case Constants.Guis.RENAME_BACKPACK:
                return new GuiBackpackRename();
            case Constants.Guis.BACKPACK:
                return FactoryBackpack.getGuiContainer(player);
        }

        return null;
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch(id) {
            case Constants.Guis.RENAME_BACKPACK:
                return null;
            case Constants.Guis.BACKPACK:
                return FactoryBackpack.getContainer(player);
        }

        return null;
    }
}
