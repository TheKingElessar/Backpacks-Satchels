package de.eydamos.backpack.factory;

import de.eydamos.backpack.data.PlayerSave;
import de.eydamos.backpack.gui.GuiBackpack;
import de.eydamos.backpack.storage.container.Boundaries;
import de.eydamos.backpack.storage.container.ContainerAdvanced;
import de.eydamos.backpack.storage.container.ContainerSpecialSlots;
import de.eydamos.backpack.storage.slot.SlotBackpackOnly;
import de.eydamos.guiadvanced.form.Label;
import de.eydamos.guiadvanced.subpart.GuiSlot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FactorySpecialSlots implements IFactory {
    public static ContainerAdvanced getContainer(EntityPlayer player) {
        ContainerAdvanced container;
        IInventory specialSlotInventory = PlayerSave.loadPlayer(player.world, player);
        container = new ContainerSpecialSlots(specialSlotInventory, player);

        int maxWidth = 160;

        // set container width (needed for gui)
        container.setWidth(maxWidth + 2 * X_SPACING);

        int x = (int) Math.round(maxWidth / 2. - SLOT / 2.) + 1;
        int y = 17; // initial space for label

        container.addBoundary(Boundaries.BACKPACK);

        // backpack slot
        container.addSlot(new SlotBackpackOnly(specialSlotInventory, 0, x, y));

        container.addBoundary(Boundaries.BACKPACK_END);

        container.addBoundary(Boundaries.INVENTORY);

        x = X_SPACING;
        y += 24;
        // player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                container.addSlot(new Slot(player.inventory, col + row * 9 + 9, x, y));
                x += SLOT;
            }
            y += SLOT;
            x = X_SPACING;
        }

        container.addBoundary(Boundaries.INVENTORY_END);
        container.addBoundary(Boundaries.HOTBAR);

        y += 6;

        // hotbar
        for (int col = 0; col < 9; col++) {
            container.addSlot(new Slot(player.inventory, col, x, y));
            x += SLOT;
        }

        container.addBoundary(Boundaries.HOTBAR_END);

        y += SLOT;
        y += 7;

        // set container height (needed for gui)
        container.setHeight(y);

        return container;
    }

    @SideOnly(Side.CLIENT)
    public static GuiContainer getGuiContainer(EntityPlayer player) {
        ContainerSpecialSlots container = (ContainerSpecialSlots) getContainer(player);
        GuiBackpack guiBackpack = new GuiBackpack(container);

        GuiSlot guiSlot;
        for (int i = 0; i < container.inventorySlots.size(); i++) {
            Slot slot = container.inventorySlots.get(i);
            guiSlot = new GuiSlot(slot.xPos - 1, slot.yPos - 1);
            guiBackpack.addSubPart(guiSlot);
        }

        guiBackpack.addSubPart(new Label(X_SPACING, 6, 0x404040, container.getInventoryToSave().getName()));

        return guiBackpack;
    }
}
