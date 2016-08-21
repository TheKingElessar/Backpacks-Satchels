package de.eydamos.backpack.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum EColor {
    NONE(0, "", Items.water_bucket, 0),
    BLACK(1, "black", Items.dye, 0),
    RED(2, "red", Items.dye, 1),
    GREEN(3, "green", Items.dye, 2),
    BROWN(4, "brown", Items.dye, 3),
    BLUE(5, "blue", Items.dye, 4),
    PURPLE(6, "purple", Items.dye, 5),
    CYAN(7, "cyan", Items.dye, 6),
    LIGHT_GRAY(8, "lightGray", Items.dye, 7),
    GRAY(9, "gray", Items.dye, 8),
    PINK(10, "pink", Items.dye, 9),
    LIME(11, "lime", Items.dye, 10),
    YELLOW(12, "yellow", Items.dye, 11),
    LIGHT_BLUE(13, "lightBlue", Items.dye, 12),
    MAGENTA(14, "magenta", Items.dye, 13),
    ORANGE(15, "orange", Items.dye, 14),
    WHITE(16, "white", Items.dye, 15);

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

    @Nullable
    public static EColor getColor(@NotNull ItemStack itemStack) {
        for (EColor color : values()) {
            if (itemStack.getItem() == color.item && itemStack.getItemDamage() == color.itemDamage) {
                return color;
            }
        }

        return null;
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
