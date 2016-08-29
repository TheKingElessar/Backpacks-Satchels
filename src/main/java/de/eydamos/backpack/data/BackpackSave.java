package de.eydamos.backpack.data;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

import java.util.Hashtable;
import java.util.Map;

public class BackpackSave extends WorldSavedData implements IInventory {
    private ItemStack[] currentInventory;
    private Hashtable<String, ItemStack[]> inventories = new Hashtable<String, ItemStack[]>();
    private ItemStack backpack;

    public BackpackSave() {
        super(Constants.INVENTORIES_PATH + "DUMMY");
    }

    public BackpackSave(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        for (String identifier : nbt.getKeySet()) {
            NBTTagList inventoryList = nbt.getTagList(identifier, Constants.NBTTypes.TAG_COMPOUND);
            ItemStack[] inventory = new ItemStack[inventoryList.tagCount()];
            for (int i = 0; i < inventoryList.tagCount(); i++) {
                NBTTagCompound slotEntry = inventoryList.getCompoundTagAt(i);
                int slot = slotEntry.getByte(Constants.NBT.SLOT) & 0xff;

                if (slot >= 0 && slot < inventory.length && slotEntry.hasKey("id")) {
                    inventory[slot] = ItemStack.loadItemStackFromNBT(slotEntry);
                }
            }

            if (currentInventory == null) {
                currentInventory = inventory;
            }

            inventories.put(identifier, inventory);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        for (Map.Entry<String, ItemStack[]> inventoryEntry : inventories.entrySet()) {
            String identifier = inventoryEntry.getKey();
            ItemStack[] inventory = inventoryEntry.getValue();

            NBTTagList inventoryList = new NBTTagList();
            for (int i = 0; i < inventory.length; i++) {
                NBTTagCompound slotEntry = new NBTTagCompound();
                slotEntry.setByte(Constants.NBT.SLOT, (byte) i);
                if (inventory[i] != null) {
                    inventory[i].writeToNBT(slotEntry);
                }
                inventoryList.appendTag(slotEntry);
            }

            nbt.setTag(identifier, inventoryList);
        }

        return nbt;
    }

    private void initialize(ItemStack backpack) {
        this.backpack = backpack;

        if (currentInventory == null) {
            int slots = BackpackHelper.getSlots(backpack);

            ItemStack[] inventory = new ItemStack[slots];
            inventories.put(Constants.NBT.BACKPACK, inventory);
            currentInventory = inventory;
        }
    }

    public static BackpackSave loadBackpack(World world, ItemStack backpack) {
        String UUID = BackpackHelper.getUUID(backpack);
        MapStorage storage = world.getMapStorage();

        BackpackSave instance = (BackpackSave) storage.getOrLoadData(BackpackSave.class, Constants.INVENTORIES_PATH + UUID);

        if (instance == null) {
            instance = new BackpackSave(Constants.INVENTORIES_PATH + UUID);
            storage.setData(Constants.INVENTORIES_PATH + UUID, instance);
        }

        instance.initialize(backpack);

        return instance;
    }

    @Override
    public int getSizeInventory() {
        return currentInventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (currentInventory != null && index >= 0 && index < currentInventory.length) {
            return currentInventory[index];
        }

        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int amount) {
        ItemStack itemstack = getStackInSlot(index);

        if (itemstack != null) {
            if (itemstack.stackSize <= amount) {
                setInventorySlotContents(index, null);
            } else {
                itemstack = itemstack.splitStack(amount);

                if (getStackInSlot(index).stackSize == 0) {
                    setInventorySlotContents(index, null);
                } else {
                    markDirty();
                }
            }
        }

        return itemstack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemstack = null;

        if (getStackInSlot(index) != null) {
            itemstack = getStackInSlot(index);
            setInventorySlotContents(index, null);
        }

        return itemstack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack newContent) {
        if (currentInventory != null && index >= 0 && index < currentInventory.length) {
            currentInventory[index] = newContent;

            if (newContent != null && newContent.stackSize > getInventoryStackLimit()) {
                newContent.stackSize = getInventoryStackLimit();
            }

            markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
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
                currentInventory[i] = null;
            }
        }
    }

    @Override
    public String getName() {
        return hasCustomName() ? backpack.getDisplayName() : backpack.getUnlocalizedName() + ".name";
    }

    @Override
    public boolean hasCustomName() {
        return backpack.hasDisplayName();
    }

    @Override
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
}
