package de.eydamos.backpack.item;

import de.eydamos.backpack.misc.EColor;
import de.eydamos.backpack.misc.ESize;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Hashtable;

public enum EBackpack {
    SMALL(ESize.SMALL, EColor.NONE),
    SMALL_BLACK(ESize.SMALL, EColor.BLACK),
    SMALL_RED(ESize.SMALL, EColor.RED),
    SMALL_GREEN(ESize.SMALL, EColor.GREEN),
    SMALL_BROWN(ESize.SMALL, EColor.BROWN),
    SMALL_BLUE(ESize.SMALL, EColor.BLUE),
    SMALL_PURPLE(ESize.SMALL, EColor.PURPLE),
    SMALL_CYAN(ESize.SMALL, EColor.CYAN),
    SMALL_LIGHT_GRAY(ESize.SMALL, EColor.LIGHT_GRAY),
    SMALL_GRAY(ESize.SMALL, EColor.GRAY),
    SMALL_PINK(ESize.SMALL, EColor.PINK),
    SMALL_LIME(ESize.SMALL, EColor.LIME),
    SMALL_YELLOW(ESize.SMALL, EColor.YELLOW),
    SMALL_LIGHT_BLUE(ESize.SMALL, EColor.LIGHT_BLUE),
    SMALL_MAGENTA(ESize.SMALL, EColor.MAGENTA),
    SMALL_ORANGE(ESize.SMALL, EColor.ORANGE),
    SMALL_WHITE(ESize.SMALL, EColor.WHITE),
    MEDIUM(ESize.MEDIUM, EColor.NONE),
    MEDIUM_BLACK(ESize.MEDIUM, EColor.BLACK),
    MEDIUM_RED(ESize.MEDIUM, EColor.RED),
    MEDIUM_GREEN(ESize.MEDIUM, EColor.GREEN),
    MEDIUM_BROWN(ESize.MEDIUM, EColor.BROWN),
    MEDIUM_BLUE(ESize.MEDIUM, EColor.BLUE),
    MEDIUM_PURPLE(ESize.MEDIUM, EColor.PURPLE),
    MEDIUM_CYAN(ESize.MEDIUM, EColor.CYAN),
    MEDIUM_LIGHT_GRAY(ESize.MEDIUM, EColor.LIGHT_GRAY),
    MEDIUM_GRAY(ESize.MEDIUM, EColor.GRAY),
    MEDIUM_PINK(ESize.MEDIUM, EColor.PINK),
    MEDIUM_LIME(ESize.MEDIUM, EColor.LIME),
    MEDIUM_YELLOW(ESize.MEDIUM, EColor.YELLOW),
    MEDIUM_LIGHT_BLUE(ESize.MEDIUM, EColor.LIGHT_BLUE),
    MEDIUM_MAGENTA(ESize.MEDIUM, EColor.MAGENTA),
    MEDIUM_ORANGE(ESize.MEDIUM, EColor.ORANGE),
    MEDIUM_WHITE(ESize.MEDIUM, EColor.WHITE),
    BIG(ESize.BIG, EColor.NONE),
    BIG_BLACK(ESize.BIG, EColor.BLACK),
    BIG_RED(ESize.BIG, EColor.RED),
    BIG_GREEN(ESize.BIG, EColor.GREEN),
    BIG_BROWN(ESize.BIG, EColor.BROWN),
    BIG_BLUE(ESize.BIG, EColor.BLUE),
    BIG_PURPLE(ESize.BIG, EColor.PURPLE),
    BIG_CYAN(ESize.BIG, EColor.CYAN),
    BIG_LIGHT_GRAY(ESize.BIG, EColor.LIGHT_GRAY),
    BIG_GRAY(ESize.BIG, EColor.GRAY),
    BIG_PINK(ESize.BIG, EColor.PINK),
    BIG_LIME(ESize.BIG, EColor.LIME),
    BIG_YELLOW(ESize.BIG, EColor.YELLOW),
    BIG_LIGHT_BLUE(ESize.BIG, EColor.LIGHT_BLUE),
    BIG_MAGENTA(ESize.BIG, EColor.MAGENTA),
    BIG_ORANGE(ESize.BIG, EColor.ORANGE),
    BIG_WHITE(ESize.BIG, EColor.WHITE);

    private static Hashtable<Integer, String> VARIANTS = new Hashtable<Integer, String>();
    private static Item item;

    protected ESize size;
    protected EColor color;
    protected String identifier;
    protected int damage;
    protected NBTTagCompound nbtTagCompound;

    EBackpack(ESize size, EColor color) {
        this.size = size;
        this.color = color;

        identifier = size.name().toLowerCase();
        if (color.getName().length() > 0) {
            identifier += '_' + color.getName();
        }

        damage = size.getDamage() + color.getDamage();
    }

    public static void setItem(Item item) {
        EBackpack.item = item;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getDamage() {
        return damage;
    }

    public ItemStack getItemStack(int amount) {
        ItemStack itemStack = new ItemStack(item, amount, damage);

        itemStack.setTagCompound(this.nbtTagCompound);

        return itemStack;
    }

    public static String getIdentifierByDamage(int damage) {
        for (EBackpack backpack : values()) {
            if (backpack.getDamage() == damage) {
                return backpack.identifier;
            }
        }

        return "";
    }

    public static Hashtable<Integer, String> getVariants() {
        return VARIANTS;
    }

    static {
        for (EBackpack backpack : values()) {
            VARIANTS.put(backpack.getDamage(), backpack.getIdentifier());
        }
    }
}
