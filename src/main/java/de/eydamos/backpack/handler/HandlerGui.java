package de.eydamos.backpack.handler;

import cofh.thermalexpansion.gui.client.storage.GuiSatchel;
import cofh.thermalexpansion.gui.container.storage.ContainerSatchel;
import de.eydamos.backpack.factory.FactoryBackpack;
import de.eydamos.backpack.factory.FactorySpecialSlots;
import de.eydamos.backpack.gui.GuiBackpackRename;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class HandlerGui implements IGuiHandler {
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case Constants.Guis.RENAME_BACKPACK:
                return new GuiBackpackRename();
            case Constants.Guis.BACKPACK:
                return FactoryBackpack.getGuiContainer(player, true);
            case Constants.Guis.SPECIAL_SLOTS:
                return FactorySpecialSlots.getGuiContainer(player);
            case Constants.Guis.CARRIED_BACKPACK:
                return FactoryBackpack.getGuiContainer(player, false);
            case Constants.Guis.CARRIED_SATCHEL:
                return new GuiSatchel(player.inventory, new ContainerSatchel(HandlerSatchels.getSatchel(player), player.inventory));
            
        }

        return null;
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case Constants.Guis.RENAME_BACKPACK:
                return null;
            case Constants.Guis.BACKPACK:
                return FactoryBackpack.getContainer(player, true);
            case Constants.Guis.SPECIAL_SLOTS:
                return FactorySpecialSlots.getContainer(player);
            case Constants.Guis.CARRIED_BACKPACK:
                return FactoryBackpack.getContainer(player, false);
            case Constants.Guis.CARRIED_SATCHEL:
                // Todo: The server must be closing it. Something wrong here?
                ContainerSatchel containerSatchel = new ContainerSatchel(HandlerSatchels.getSatchel(player), player.inventory);
                if (containerSatchel == null)
                {
                    System.out.println("Server GUI thing is NULL");
                }
                return containerSatchel;
            
        }

        return null;
    }
}
