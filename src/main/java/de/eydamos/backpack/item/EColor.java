package de.eydamos.backpack.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum EColor {
    NONE(0, "", Items.WATER_BUCKET, 0),
    BLACK(1, "black", Items.DYE, 0),
    RED(2, "red", Items.DYE, 1),
    GREEN(3, "green", Items.DYE, 2),
    BROWN(4, "brown", Items.DYE, 3),
    BLUE(5, "blue", Items.DYE, 4),
    PURPLE(6, "purple", Items.DYE, 5),
    CYAN(7, "cyan", Items.DYE, 6),
    LIGHT_GRAY(8, "lightGray", Items.DYE, 7),
    GRAY(9, "gray", Items.DYE, 8),
    PINK(10, "pink", Items.DYE, 9),
    LIME(11, "lime", Items.DYE, 10),
    YELLOW(12, "yellow", Items.DYE, 11),
    LIGHT_BLUE(13, "lightBlue", Items.DYE, 12),
    MAGENTA(14, "magenta", Items.DYE, 13),
    ORANGE(15, "orange", Items.DYE, 14),
    WHITE(16, "white", Items.DYE, 15);

    protected final int damage;
    protected final String name;
    protected final Item item;
    protected final int itemDamage;

    EColor(int damage, String name, Item item, int itemDamage) {
        this.damage = damage;
        this.name = name;
        this.item = item;
        this.itemDamage = itemDamage;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public static EColor getColor(ItemStack itemStack) {
        for (EColor color : values()) {
            if (itemStack.getItem() == color.item && itemStack.getItemDamage() == color.itemDamage) {
                return color;
            }
        }

        return null;
    }

    public static EColor getColorByBackpack(ItemStack itemStack) {
        int colorDamage = itemStack.getItemDamage() % 100;

        for (EColor color : values()) {
            if (colorDamage == color.damage) {
                return color;
            }
        }

        return EColor.NONE;
    }

    public static boolean isColor(ItemStack itemStack) {
        for (EColor color : values()) {
            if (getColor(itemStack) != null) {
                return true;
            }
        }

        return false;
    }
}
