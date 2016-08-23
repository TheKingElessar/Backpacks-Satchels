package de.eydamos.backpack.data;

import de.eydamos.backpack.misc.Constants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class PlayerSave extends WorldSavedData {
    public PlayerSave() {
        super(Constants.PLAYERS_PATH + "DUMMY");
    }

    public PlayerSave(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {

    }

    public static PlayerSave loadPlayer(World world, String UUID) {
        MapStorage storage = world.getMapStorage();

        PlayerSave instance = (PlayerSave) storage.loadData(PlayerSave.class, Constants.PLAYERS_PATH + UUID);

        if (instance == null) {
            instance = new PlayerSave(Constants.PLAYERS_PATH + UUID);
            storage.setData(Constants.PLAYERS_PATH + UUID, instance);
        }

        return instance;
    }
}
