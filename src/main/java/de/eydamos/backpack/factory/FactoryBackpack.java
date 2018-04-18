package de.eydamos.backpack.factory;

import de.eydamos.backpack.gui.GuiBackpack;
import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.storage.container.Boundaries;
import de.eydamos.backpack.storage.container.ContainerAdvanced;
import de.eydamos.backpack.storage.slot.SlotBackpack;
import de.eydamos.guiadvanced.form.Label;
import de.eydamos.guiadvanced.subpart.GuiSlot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FactoryBackpack implements IFactory {
    public static ContainerAdvanced getContainer(EntityPlayer player, boolean heldItem) {
        ItemStack backpack = BackpackHelper.getBackpackFromPlayer(player, heldItem);

        if (backpack.isEmpty()) {
            return null;
        }

        ContainerAdvanced container;
        IInventory backpackInventory = BackpackHelper.getInventory(player, heldItem);
        container = new ContainerAdvanced(backpackInventory, player);

        int slotsPerRow = BackpackHelper.getSlotsPerRow(backpack);
        int inventoryRows = (int) Math.ceil(backpackInventory.getSizeInventory() / (float) slotsPerRow);
        int maxWidth = Math.max(9, slotsPerRow) * SLOT;

        // set container width (needed for gui)
        container.setWidth(maxWidth + 2 * X_SPACING);

        int x = X_SPACING;
        int y = 17; // initial space for label

        container.addBoundary(Boundaries.BACKPACK);

        int remainingSlots = backpackInventory.getSizeInventory();
        // backpack inventory
        for (int row = 0; row < inventoryRows; row++) {
            int cols = remainingSlots >= slotsPerRow ? slotsPerRow : remainingSlots;
            remainingSlots -= cols;
            if (cols * SLOT < maxWidth/* && !hasScrollbar */) {
                x += (int) Math.round(maxWidth / 2. - cols * SLOT / 2.) + 1;
            }
            for (int col = 0; col < cols; ++col) {
                container.addSlot(new SlotBackpack(backpackInventory, col + row * slotsPerRow, x, y));
                x += SLOT;
            }
            y += SLOT;
            x = X_SPACING;
        }

        container.addBoundary(Boundaries.BACKPACK_END);
        container.addBoundary(Boundaries.INVENTORY);

        y += 14; // space for label

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
    public static GuiContainer getGuiContainer(EntityPlayer player, boolean heldItem) {
        ItemStack backpack = BackpackHelper.getBackpackFromPlayer(player, heldItem);

        if (backpack.isEmpty()) {
            return null;
        }

        ContainerAdvanced container = getContainer(player, heldItem);
        GuiBackpack guiBackpack = new GuiBackpack(container);

        int slotsPerRow = BackpackHelper.getSlotsPerRow(backpack);
        int inventoryRows = (int) Math.ceil(container.getInventoryToSave().getSizeInventory() / (float) slotsPerRow);
        int textPositionY = 17 + inventoryRows * SLOT + 2;

        GuiSlot guiSlot;
        for (int i = 0; i < container.inventorySlots.size(); i++) {
            Slot slot = container.inventorySlots.get(i);
            guiSlot = new GuiSlot(slot.xPos - 1, slot.yPos - 1);
            guiBackpack.addSubPart(guiSlot);
        }

        guiBackpack.addSubPart(new Label(X_SPACING, 6, 0x404040, container.getInventoryToSave().getName()));
        guiBackpack.addSubPart(new Label(X_SPACING, textPositionY, 0x404040, "container.inventory"));

        return guiBackpack;
    }
}
