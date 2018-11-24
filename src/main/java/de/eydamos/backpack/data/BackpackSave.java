package de.eydamos.backpack.data;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.helper.ItemStackHelper;
import de.eydamos.backpack.misc.Constants;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Hashtable;
import java.util.Map;

public class BackpackSave extends WorldSavedData implements IInventory {
    private ItemStack[] currentInventory;

    private Hashtable<String, ItemStack[]> inventories = new Hashtable<>();

    private ItemStack backpack;

    private boolean heldItem;

    @Nullable private EntityPlayer player;

    public BackpackSave() {
        super(Constants.INVENTORIES_PATH + "DUMMY");
    }

    public BackpackSave(String name) {
        super(name);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void readFromNBT(NBTTagCompound nbt) {
        for (String identifier : nbt.getKeySet()) {
            NBTTagList inventoryList = nbt.getTagList(identifier, Constants.NBTTypes.TAG_COMPOUND);
            ItemStack[] inventory = ItemStackHelper.createInventory(inventoryList.tagCount());
            for (int i = 0; i < inventoryList.tagCount(); i++) {
                NBTTagCompound slotEntry = inventoryList.getCompoundTagAt(i);
                int slot = slotEntry.getByte(Constants.NBT.SLOT) & 0xff;

                if (slot >= 0 && slot < inventory.length && slotEntry.hasKey("id")) {
                    inventory[slot] = new ItemStack(slotEntry);
                }
            }

            if (currentInventory == null) {
                currentInventory = inventory;
            }

            inventories.put(identifier, inventory);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        for (Map.Entry<String, ItemStack[]> inventoryEntry : inventories.entrySet()) {
            String identifier = inventoryEntry.getKey();
            ItemStack[] inventory = inventoryEntry.getValue();

            NBTTagList inventoryList = new NBTTagList();
            for (int i = 0; i < inventory.length; i++) {
                NBTTagCompound slotEntry = new NBTTagCompound();
                slotEntry.setByte(Constants.NBT.SLOT, (byte) i);
                if (!inventory[i].isEmpty()) {
                    inventory[i].writeToNBT(slotEntry);
                }
                inventoryList.appendTag(slotEntry);
            }

            nbt.setTag(identifier, inventoryList);
        }

        return nbt;
    }

    private void initialize(ItemStack backpack, @Nullable EntityPlayer player, boolean heldItem) {
        this.backpack = backpack;
        this.player = player;
        this.heldItem = heldItem;

        if (currentInventory == null) {
            int slots = BackpackHelper.getSlots(backpack);

            ItemStack[] inventory = ItemStackHelper.createInventory(slots);
            inventories.put(Constants.NBT.BACKPACK, inventory);
            currentInventory = inventory;
        }
    }

    public static BackpackSave loadBackpack(World world, ItemStack backpack, @Nullable EntityPlayer player, boolean heldItem) {
        String UUID = BackpackHelper.getUUID(backpack);
        MapStorage storage = world.getMapStorage();

        BackpackSave instance = (BackpackSave) storage.getOrLoadData(
            BackpackSave.class,
            Constants.INVENTORIES_PATH + UUID
        );

        if (instance == null) {
            instance = new BackpackSave(Constants.INVENTORIES_PATH + UUID);
            storage.setData(Constants.INVENTORIES_PATH + UUID, instance);
        }

        instance.initialize(backpack, player, heldItem);

        return instance;
    }

    @Override
    public int getSizeInventory() {
        return currentInventory.length;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.currentInventory) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public ItemStack getStackInSlot(int index) {
        if (currentInventory != null && index >= 0 && index < currentInventory.length) {
            return currentInventory[index];
        }

        return ItemStack.EMPTY;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public ItemStack decrStackSize(int index, int amount) {
        ItemStack itemstack = getStackInSlot(index);

        if (!itemstack.isEmpty()) {
            if (itemstack.getCount() <= amount) {
                setInventorySlotContents(index, ItemStack.EMPTY);
            } else {
                itemstack = itemstack.splitStack(amount);

                if (getStackInSlot(index).getCount() == 0) {
                    setInventorySlotContents(index, ItemStack.EMPTY);
                } else {
                    markDirty();
                }
            }
        }

        return itemstack;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemstack = ItemStack.EMPTY;

        if (!getStackInSlot(index).isEmpty()) {
            itemstack = getStackInSlot(index);
            setInventorySlotContents(index, ItemStack.EMPTY);
        }

        return itemstack;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void setInventorySlotContents(int index, ItemStack newContent) {
        if (currentInventory != null && index >= 0 && index < currentInventory.length) {
            currentInventory[index] = newContent;

            if (!newContent.isEmpty() && newContent.getCount() > getInventoryStackLimit()) {
                newContent.setCount(getInventoryStackLimit());
            }

            updateUsedSlots();
            markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void openInventory(EntityPlayer player) {
    }

    @Override
    @ParametersAreNonnullByDefault
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        if (currentInventory != null) {
            for (int i = 0; i < currentInventory.length; ++i) {
                currentInventory[i] = ItemStack.EMPTY;
            }
        }
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getName() {
        return hasCustomName() ? backpack.getDisplayName() : backpack.getUnlocalizedName() + ".name";
    }

    @Override
    public boolean hasCustomName() {
        return backpack.hasDisplayName();
    }

    @Override
    @MethodsReturnNonnullByDefault
    public ITextComponent getDisplayName() {
        ITextComponent component;
        if (hasCustomName()) {
            component = new TextComponentString(backpack.getDisplayName());
        } else {
            component = new TextComponentTranslation(backpack.getUnlocalizedName());
        }

        return component;
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    private void updateUsedSlots() {
        int slotsUsed = 0;
        for (ItemStack itemStack : currentInventory) {
            if (!itemStack.isEmpty()) {
                slotsUsed++;
            }
        }

        BackpackHelper.setSlotsUsed(player == null ? backpack : BackpackHelper.getBackpackFromPlayer(player, heldItem), slotsUsed);
    }
}
