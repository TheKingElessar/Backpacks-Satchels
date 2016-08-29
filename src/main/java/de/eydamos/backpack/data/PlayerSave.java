package de.eydamos.backpack.data;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import de.eydamos.backpack.util.GeneralUtil;
import de.eydamos.backpack.util.NBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class PlayerSave extends WorldSavedData implements IInventory {
    private EntityPlayer player;
    private ItemStack[] inventory;

    public PlayerSave() {
        super(Constants.PLAYERS_PATH + "DUMMY");
    }

    public PlayerSave(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        inventory = new ItemStack[2];

        if (NBTUtil.hasTag(nbt, Constants.NBT.BACKPACK)) {
            NBTTagCompound backpack = NBTUtil.getCompoundTag(nbt, Constants.NBT.BACKPACK);
            inventory[0] = ItemStack.loadItemStackFromNBT(backpack);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if (inventory != null && inventory[0] != null) {
            NBTTagCompound backpack = new NBTTagCompound();
            inventory[0].writeToNBT(backpack);

            NBTUtil.setCompoundTag(nbt, Constants.NBT.BACKPACK, backpack);
        }

        return nbt;
    }

    private void initialize(EntityPlayer player) {
        this.player = player;

        if (inventory == null) {
            inventory = new ItemStack[2];
        }
    }

    public ItemStack getBackpack() {
        if (inventory != null) {
            return inventory[0];
        }

        return null;
    }

    public static PlayerSave loadPlayer(World world, EntityPlayer player) {
        String UUID = GeneralUtil.getPlayerUUID(player);
        MapStorage storage = world.getMapStorage();

        PlayerSave instance = (PlayerSave) storage.getOrLoadData(PlayerSave.class, Constants.PLAYERS_PATH + UUID);

        if (instance == null) {
            instance = new PlayerSave(Constants.PLAYERS_PATH + UUID);
            storage.setData(Constants.PLAYERS_PATH + UUID, instance);
        }

        instance.initialize(player);

        return instance;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (inventory != null && index >= 0 && index < inventory.length) {
            return inventory[index];
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
        if (inventory != null && index >= 0 && index < inventory.length) {
            inventory[index] = newContent;

            if (newContent != null && newContent.stackSize > getInventoryStackLimit()) {
                newContent.stackSize = getInventoryStackLimit();
            }

            markDirty();

            if (index == 0 && GeneralUtil.isServerSide(player.worldObj)) {
                Backpack.packetHandler.propagateCarriedBackpack(player);
            }
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
        if (inventory != null) {
            for (int i = 0; i < inventory.length; ++i) {
                inventory[i] = null;
            }
        }
    }

    @Override
    public String getName() {
        return Localizations.INVENTORY_SPECIAL_SLOTS;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation(getName());
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }
}
