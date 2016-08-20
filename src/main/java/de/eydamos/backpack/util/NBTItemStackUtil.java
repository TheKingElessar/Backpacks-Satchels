package de.eydamos.backpack.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTItemStackUtil {
    /**
     * Initializes the {@link NBTTagCompound} for the given {@link ItemStack} if it is null.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     */
    private static void initNBTTagCompound(ItemStack itemStack) {
        if (itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    /**
     * Checks if the {@link NBTTagCompound} of the given {@link ItemStack} has a given tag.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be checked.
     *
     * @return True if the {@link NBTTagCompound} has the tag otherwise false.
     */
    public static boolean hasTag(ItemStack itemStack, String tagName) {
        return itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey(tagName);
    }

    /**
     * Removes the given tag from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be removed.
     */
    public static void removeTag(ItemStack itemStack, String tagName) {
        if (itemStack.getTagCompound() != null) {
            itemStack.getTagCompound().removeTag(tagName);
        }
    }

    /**
     * Gets a {@link String} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a String or an empty String if the NBTTagCompound has no such key.
     */
    public static String getString(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getString(tagName);
        }

        return "";
    }

    /**
     * Sets the given {@link String} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as a {@link String}.
     */
    public static void setString(ItemStack itemStack, String tagName, String tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setString(tagName, tagValue);
    }

    /**
     * Gets a {@link Boolean} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a Boolean or false if the NBTTagCompound has no such key.
     */
    public static Boolean getBoolean(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getBoolean(tagName);
        }

        return false;
    }

    /**
     * Sets the given {@link Boolean} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as a {@link Boolean}.
     */
    public static void setBoolean(ItemStack itemStack, String tagName, boolean tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setBoolean(tagName, tagValue);
    }

    /**
     * Gets a {@link Byte} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a Byte or 0 if the NBTTagCompound has no such key.
     */
    public static Byte getByte(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getByte(tagName);
        }

        return (byte) 0;
    }

    /**
     * Sets the given {@link Byte} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as a {@link Byte}.
     */
    public static void setByte(ItemStack itemStack, String tagName, byte tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setByte(tagName, tagValue);
    }

    /**
     * Gets a {@link Short} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a Short or 0 if the NBTTagCompound has no such key.
     */
    public static Short getShort(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getShort(tagName);
        }

        return (short) 0;
    }

    /**
     * Sets the given {@link Short} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as a {@link Short}.
     */
    public static void setShort(ItemStack itemStack, String tagName, short tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setShort(tagName, tagValue);
    }

    /**
     * Gets a {@link Integer} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as an Integer or 0 if the NBTTagCompound has no such key.
     */
    public static Integer getInteger(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getInteger(tagName);
        }

        return 0;
    }

    /**
     * Sets the given {@link Integer} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as an {@link Integer}.
     */
    public static void setInteger(ItemStack itemStack, String tagName, int tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setInteger(tagName, tagValue);
    }

    /**
     * Gets a {@link Long} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a Long or 0 if the NBTTagCompound has no such key.
     */
    public static Long getLong(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getLong(tagName);
        }

        return (long) 0;
    }

    /**
     * Sets the given {@link Long} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as a {@link Long}.
     */
    public static void setLong(ItemStack itemStack, String tagName, long tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setLong(tagName, tagValue);
    }

    /**
     * Gets a {@link Float} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a Float or 0 if the NBTTagCompound has no such key.
     */
    public static Float getFloat(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getFloat(tagName);
        }

        return (float) 0;
    }

    /**
     * Sets the given {@link Float} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as a {@link Float}.
     */
    public static void setFloat(ItemStack itemStack, String tagName, float tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setFloat(tagName, tagValue);
    }

    /**
     * Gets a {@link Double} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a Double or 0 if the NBTTagCompound has no such key.
     */
    public static Double getDouble(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getDouble(tagName);
        }

        return (double) 0;
    }

    /**
     * Sets the given {@link Double} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as a {@link Double}.
     */
    public static void setDouble(ItemStack itemStack, String tagName, double tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setDouble(tagName, tagValue);
    }

    /**
     * Gets a {@link NBTTagCompound} with the given tag name from the {@link NBTTagCompound} of the given
     * {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a NBTTagCompound or an empty NBTTagCompound if the NBTTagCompound has no
     * such key.
     */
    public static NBTTagCompound getCompoundTag(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getCompoundTag(tagName);
        }

        return new NBTTagCompound();
    }

    /**
     * Sets the given {@link NBTTagCompound} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag which should be set.
     * @param tagValue  The value which should be set to the given tag as a {@link NBTTagCompound}.
     */
    public static void setCompoundTag(ItemStack itemStack, String tagName, NBTTagCompound tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setTag(tagName, tagValue);
    }

    /**
     * Gets a {@link NBTTagList} with the given tag name from the {@link NBTTagCompound} of the given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     *
     * @return The value of the given tag as a NBTTagList or an empty NBTTagList if the NBTTagCompound has no such key.
     */
    public static NBTTagList getTagList(ItemStack itemStack, String tagName, int tagType) {
        if (hasTag(itemStack, tagName)) {
            return itemStack.getTagCompound().getTagList(tagName, tagType);
        }

        return new NBTTagList();
    }

    /**
     * Sets the given {@link NBTTagList} with the given tag name on the {@link NBTTagCompound} of the given
     * {@link ItemStack}. If the {@link NBTTagCompound} is null it will be initialized.
     *
     * @param itemStack The {@link ItemStack} which holds the {@link NBTTagCompound}.
     * @param tagName   The name of the tag for which the value should be determined.
     * @param tagValue  The value which should be set to the given tag as a {@link NBTTagList}.
     */
    public static void setTagList(ItemStack itemStack, String tagName, NBTTagList tagValue) {
        initNBTTagCompound(itemStack);
        itemStack.getTagCompound().setTag(tagName, tagValue);
    }
}
