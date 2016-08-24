package de.eydamos.backpack.helper;

import de.eydamos.backpack.item.ESize;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import de.eydamos.backpack.util.NBTItemStackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;

import java.util.UUID;

public class BackpackHelper {
    public static void initialize(ItemStack backpack, ItemStack leather, ItemStack frame) {
        TierLeather tierLeather = TierLeather.getTierByItemStack(leather);
        TierFrame tierFrame = TierFrame.getTierByItemStack(frame);

        initialize(backpack, tierLeather, tierFrame);
    }

    public static void initialize(ItemStack backpack, TierLeather leather, TierFrame frame) {
        if (!isBackpack(backpack)) {
            FMLLog.warning("ItemStack is null or not a backpack.");
            return;
        }

        ESize size = ESize.getSizeByBackpack(backpack);
        if (size == null || leather == null || frame == null) {
            FMLLog.warning("Mandatory data missing can't initialize backpack.");
            return;
        }

        leather.setTier(backpack);
        frame.setTier(backpack);

        int slots = leather.getBaseSlots() * size.getSlotMultiplier();
        NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS, slots);
        NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_USED, 0);

        NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_PER_ROW, 9);

        NBTItemStackUtil.setInteger(backpack, Constants.NBT.MODULE_SLOTS, size.getModuleSlots());

        NBTItemStackUtil.setString(backpack, Constants.NBT.UID, UUID.randomUUID().toString());
    }

    public static int getSlots(ItemStack itemStack) {
        if (isBackpack(itemStack)) {
            return NBTItemStackUtil.getInteger(itemStack, Constants.NBT.SLOTS);
        }

        return 0;
    }

    public static int getSlotsUsed(ItemStack itemStack) {
        if (isBackpack(itemStack)) {
            return NBTItemStackUtil.getInteger(itemStack, Constants.NBT.SLOTS_USED);
        }

        return 0;
    }

    public static boolean isBackpack(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() == BackpackItems.backpack;
    }
}
