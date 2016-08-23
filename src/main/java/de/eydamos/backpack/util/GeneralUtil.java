package de.eydamos.backpack.util;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class GeneralUtil {
    public static boolean isServerSide() {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
    }

    public static boolean isServerSide(World world) {
        return !world.isRemote;
    }
}
