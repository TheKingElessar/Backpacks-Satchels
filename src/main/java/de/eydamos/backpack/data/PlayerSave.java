package de.eydamos.backpack.data;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.helper.ItemStackHelper;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import de.eydamos.backpack.util.GeneralUtil;
import de.eydamos.backpack.util.NBTUtil;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.ParametersAreNonnullByDefault;

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
    @ParametersAreNonnullByDefault
    public void readFromNBT(NBTTagCompound nbt) {
        inventory = ItemStackHelper.createInventory(1);

        if (NBTUtil.hasTag(nbt, Constants.NBT.BACKPACK)) {
            NBTTagCompound backpack = NBTUtil.getCompoundTag(nbt, Constants.NBT.BACKPACK);
            inventory[0] = new ItemStack(backpack);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if (inventory != null && !inventory[0].isEmpty()) {
            NBTTagCompound backpack = new NBTTagCompound();
            inventory[0].writeToNBT(backpack);

            NBTUtil.setCompoundTag(nbt, Constants.NBT.BACKPACK, backpack);
        }

        return nbt;
    }

    private void initialize(EntityPlayer player) {
        this.player = player;

        if (inventory == null) {
            inventory = ItemStackHelper.createInventory(1);
        }
    }

    public ItemStack getBackpack() {
        if (inventory != null) {
            return inventory[0];
        }

        return ItemStack.EMPTY;
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
    public boolean isEmpty() {
        for (ItemStack itemstack : this.inventory) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (inventory != null && index >= 0 && index < inventory.length) {
            return inventory[index];
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
        if (inventory != null && index >= 0 && index < inventory.length) {
            inventory[index] = newContent;

            if (!newContent.isEmpty() && newContent.getCount() > getInventoryStackLimit()) {
                newContent.setCount(getInventoryStackLimit());
            }

            markDirty();

            if (index == 0 && GeneralUtil.isServerSide(player.world)) {
                Backpack.packetHandler.propagateCarriedBackpack(player);
            }
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
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
        if (inventory != null) {
            for (int i = 0; i < inventory.length; ++i) {
                inventory[i] = ItemStack.EMPTY;
            }
        }
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getName() {
        return Localizations.INVENTORY_SPECIAL_SLOTS;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation(getName());
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }
}
