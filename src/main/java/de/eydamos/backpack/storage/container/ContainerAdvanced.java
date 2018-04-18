package de.eydamos.backpack.storage.container;

import de.eydamos.backpack.storage.slot.SlotPhantom;
import invtweaks.api.container.ChestContainer;
import invtweaks.api.container.ContainerSection;
import invtweaks.api.container.ContainerSectionCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ChestContainer
public class ContainerAdvanced extends Container {
    protected int width;

    protected int height;

    protected IInventory inventory;

    protected EntityPlayer player;

    protected Map<Boundaries, Integer> boundaries = new HashMap<>();

    public ContainerAdvanced(IInventory inventoryToSave, EntityPlayer player) {
        inventory = inventoryToSave;
        inventory.openInventory(player);
        this.player = player;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        if (inventory != null) {
            inventory.closeInventory(player);
        }

        super.onContainerClosed(player);
    }

    /**
     * Called when a player shift-clicks on a slot.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotPos) {
        ItemStack returnStack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotPos);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            returnStack = itemStack.copy();

            if (boundaries.containsKey(Boundaries.EXTRA)
                && slotPos >= boundaries.get(Boundaries.EXTRA)
                && slotPos < boundaries.get(Boundaries.EXTRA_END)) {
                if (!mergeItemStack(
                    itemStack,
                    boundaries.get(Boundaries.BACKPACK),
                    boundaries.get(Boundaries.BACKPACK_END),
                    false
                )) { // to backpack
                    if (!mergeItemStack(
                        itemStack,
                        boundaries.get(Boundaries.HOTBAR),
                        boundaries.get(Boundaries.HOTBAR_END),
                        true
                    )) { // to hotbar
                        if (!mergeItemStack(
                            itemStack,
                            boundaries.get(Boundaries.INVENTORY),
                            boundaries.get(Boundaries.INVENTORY_END),
                            false
                        )) { // to inventory
                            return ItemStack.EMPTY;
                        }
                    }
                }

                slot.onSlotChange(itemStack, returnStack);
            } else if (slotPos >= boundaries.get(Boundaries.BACKPACK)
                && slotPos < boundaries.get(Boundaries.BACKPACK_END)) { // from backpack
                if (!mergeItemStack(
                    itemStack,
                    boundaries.get(Boundaries.HOTBAR),
                    boundaries.get(Boundaries.HOTBAR_END),
                    true
                )) { // to hotbar
                    if (!mergeItemStack(
                        itemStack,
                        boundaries.get(Boundaries.INVENTORY),
                        boundaries.get(Boundaries.INVENTORY_END),
                        false
                    )) { // to inventory
                        return ItemStack.EMPTY;
                    }
                }
            } else if (slotPos >= boundaries.get(Boundaries.INVENTORY)
                && slotPos < boundaries.get(Boundaries.INVENTORY_END)) { // from inventory
                if (!mergeItemStack(
                    itemStack,
                    boundaries.get(Boundaries.BACKPACK),
                    boundaries.get(Boundaries.BACKPACK_END),
                    false
                )) { // to backpack
                    if (!mergeItemStack(
                        itemStack,
                        boundaries.get(Boundaries.HOTBAR),
                        boundaries.get(Boundaries.HOTBAR_END),
                        true
                    )) { // to hotbar
                        return ItemStack.EMPTY;
                    }
                }
            } else if (slotPos >= boundaries.get(Boundaries.HOTBAR)
                && slotPos < boundaries.get(Boundaries.HOTBAR_END)) { // from hotbar
                if (!mergeItemStack(
                    itemStack,
                    boundaries.get(Boundaries.BACKPACK),
                    boundaries.get(Boundaries.BACKPACK_END),
                    false
                )) { // to backpack
                    if (!mergeItemStack(
                        itemStack,
                        boundaries.get(Boundaries.INVENTORY),
                        boundaries.get(Boundaries.INVENTORY_END),
                        false
                    )) { // to inventory
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                return ItemStack.EMPTY;
            }

            if (itemStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(entityPlayer, itemStack);
        }

        return returnStack;
    }

    /**
     * Copy of mergeItemStack from class Container, which will ignore disabled slots.
     */
    @Override
    protected boolean mergeItemStack(ItemStack sourceStack, int firstSlot, int lastSlot, boolean backwards) {
        boolean result = false;
        int currentSlotIndex = firstSlot;

        if (backwards) {
            currentSlotIndex = lastSlot - 1;
        }

        Slot slot;
        ItemStack slotStack;

        if (sourceStack.isStackable()) {
            while (sourceStack.getCount() > 0 && (!backwards && currentSlotIndex < lastSlot
                || backwards && currentSlotIndex >= firstSlot)) {
                slot = inventorySlots.get(currentSlotIndex);
                if (/*!(slot instanceof SlotScrolling && ((SlotScrolling) slot).isDisabled()) && */slot.isItemValid(
                    sourceStack)) {
                    slotStack = slot.getStack();

                    if (!slotStack.isEmpty()
                        && slotStack.getItem() == sourceStack.getItem()
                        && (!sourceStack.getHasSubtypes() || sourceStack.getItemDamage() == slotStack.getItemDamage())
                        && ItemStack.areItemStackTagsEqual(sourceStack, slotStack)) {
                        int l = slotStack.getCount() + sourceStack.getCount();

                        if (l <= sourceStack.getMaxStackSize()) {
                            sourceStack.setCount(0);
                            slotStack.setCount(l);
                            slot.onSlotChanged();
                            result = true;
                        } else if (slotStack.getCount() < sourceStack.getMaxStackSize()) {
                            sourceStack.shrink(sourceStack.getMaxStackSize() - slotStack.getCount());
                            slotStack.setCount(sourceStack.getMaxStackSize());
                            slot.onSlotChanged();
                            result = true;
                        }
                    }
                }

                if (backwards) {
                    --currentSlotIndex;
                } else {
                    ++currentSlotIndex;
                }
            }
        }

        if (sourceStack.getCount() > 0) {
            if (backwards) {
                currentSlotIndex = lastSlot - 1;
            } else {
                currentSlotIndex = firstSlot;
            }

            while (!backwards && currentSlotIndex < lastSlot || backwards && currentSlotIndex >= firstSlot) {
                slot = inventorySlots.get(currentSlotIndex);
                if (/*!(slot instanceof SlotScrolling && ((SlotScrolling) slot).isDisabled()) && */slot.isItemValid(
                    sourceStack)) {
                    slotStack = slot.getStack();

                    if (slotStack.isEmpty()) {
                        slot.putStack(sourceStack.copy());
                        slot.onSlotChanged();
                        sourceStack.setCount(0);
                        result = true;
                        break;
                    }
                }

                if (backwards) {
                    --currentSlotIndex;
                } else {
                    ++currentSlotIndex;
                }
            }
        }

        return result;
    }

    @Override
    public ItemStack slotClick(int slotIndex, int dragType, ClickType clickType, EntityPlayer player) {
        Slot slot = slotIndex < 0 || slotIndex >= inventorySlots.size() ? null : inventorySlots.get(slotIndex);
        if (slot instanceof SlotPhantom) {
            slotPhantomClick(slot, dragType, clickType, player.inventory.getItemStack());
            return ItemStack.EMPTY;
        }
        return super.slotClick(slotIndex, dragType, clickType, player);
    }

    /**
     * Handles clicking on a phantom slot.
     *
     * @param slot      The slot that has been clicked.
     * @param dragType  The mouse button identifier: 0: left click 1: right click & left click during drag and drop 2:
     *                  middle click (scrollwheel)
     * @param clickType The mouse modifier: 0: normal click 3: drag and drop middle click 5: drag and drop left or right
     *                  click
     * @param stackHeld The stack that the player holds on his mouse.
     */
    protected void slotPhantomClick(Slot slot, int dragType, ClickType clickType, ItemStack stackHeld) {
        if (((SlotPhantom) slot).canChangeStack()) {
            // TODO find out how to reimplement this
            /*
            if (mouseButton == 2) { // middle click scrollwheel
                slot.putStack(null);
            } else {
                ItemStack phantomStack = null;

                if (stackHeld != null) {
                    phantomStack = stackHeld.copy();
                    phantomStack.stackSize = 1;
                }

                slot.putStack(phantomStack);
            }
            slot.onSlotChanged();
            */
        }
    }

    public void addSlot(Slot slot) {
        addSlotToContainer(slot);
    }

    public void addBoundary(Boundaries boundary) {
        boundaries.put(boundary, inventorySlots.size());
    }

    public int getBoundary(Boundaries boundary) {
        if (boundaries.containsKey(boundary)) {
            return boundaries.get(boundary);
        }
        return -1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int value) {
        width = value;
    }

    public void setHeight(int value) {
        height = value;
    }

    public IInventory getInventoryToSave() {
        return inventory;
    }

    @ContainerSectionCallback
    public Map<ContainerSection, List<Slot>> getContainerSections() {
        Map<ContainerSection, List<Slot>> slotRefs = new HashMap<>();

        if (boundaries.containsKey(Boundaries.CRAFTING)) {
            slotRefs.put(ContainerSection.CRAFTING_OUT, inventorySlots.subList(0, 1));
            slotRefs.put(
                ContainerSection.CRAFTING_IN_PERSISTENT,
                inventorySlots.subList(getBoundary(Boundaries.CRAFTING), getBoundary(Boundaries.CRAFTING_END))
            );
        }
        slotRefs.put(
            ContainerSection.INVENTORY,
            inventorySlots.subList(getBoundary(Boundaries.INVENTORY), getBoundary(Boundaries.HOTBAR_END))
        );
        slotRefs.put(
            ContainerSection.INVENTORY_NOT_HOTBAR,
            inventorySlots.subList(getBoundary(Boundaries.INVENTORY), getBoundary(Boundaries.INVENTORY_END))
        );
        slotRefs.put(
            ContainerSection.INVENTORY_HOTBAR,
            inventorySlots.subList(getBoundary(Boundaries.HOTBAR), getBoundary(Boundaries.HOTBAR_END))
        );
        slotRefs.put(
            ContainerSection.CHEST,
            inventorySlots.subList(getBoundary(Boundaries.BACKPACK), getBoundary(Boundaries.BACKPACK_END))
        );

        return slotRefs;
    }
}
