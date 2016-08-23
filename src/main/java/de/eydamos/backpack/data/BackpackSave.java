package de.eydamos.backpack.data;

import de.eydamos.backpack.misc.Constants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class BackpackSave extends WorldSavedData {
    public BackpackSave() {
        super(Constants.INVENTORIES_PATH + "DUMMY");
    }

    public BackpackSave(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {

    }

    public static BackpackSave loadBackpack(World world, String UUID) {
        MapStorage storage = world.getMapStorage();

        BackpackSave instance = (BackpackSave) storage.loadData(BackpackSave.class, Constants.INVENTORIES_PATH + UUID);

        if (instance == null) {
            instance = new BackpackSave(Constants.INVENTORIES_PATH + UUID);
            storage.setData(Constants.INVENTORIES_PATH + UUID, instance);
        }

        return instance;
    }
}
