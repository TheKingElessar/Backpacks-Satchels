package de.eydamos.backpack.item;

import de.eydamos.backpack.Features;
import de.eydamos.backpack.init.BackpackItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Hashtable;

public enum EBackpack {
    SMALL(ESize.SMALL, EColor.NONE, Features.BACKPACK_SMALL),
    SMALL_BLACK(ESize.SMALL, EColor.BLACK, Features.BACKPACK_SMALL),
    SMALL_RED(ESize.SMALL, EColor.RED, Features.BACKPACK_SMALL),
    SMALL_GREEN(ESize.SMALL, EColor.GREEN, Features.BACKPACK_SMALL),
    SMALL_BROWN(ESize.SMALL, EColor.BROWN, Features.BACKPACK_SMALL),
    SMALL_BLUE(ESize.SMALL, EColor.BLUE, Features.BACKPACK_SMALL),
    SMALL_PURPLE(ESize.SMALL, EColor.PURPLE, Features.BACKPACK_SMALL),
    SMALL_CYAN(ESize.SMALL, EColor.CYAN, Features.BACKPACK_SMALL),
    SMALL_LIGHT_GRAY(ESize.SMALL, EColor.LIGHT_GRAY, Features.BACKPACK_SMALL),
    SMALL_GRAY(ESize.SMALL, EColor.GRAY, Features.BACKPACK_SMALL),
    SMALL_PINK(ESize.SMALL, EColor.PINK, Features.BACKPACK_SMALL),
    SMALL_LIME(ESize.SMALL, EColor.LIME, Features.BACKPACK_SMALL),
    SMALL_YELLOW(ESize.SMALL, EColor.YELLOW, Features.BACKPACK_SMALL),
    SMALL_LIGHT_BLUE(ESize.SMALL, EColor.LIGHT_BLUE, Features.BACKPACK_SMALL),
    SMALL_MAGENTA(ESize.SMALL, EColor.MAGENTA, Features.BACKPACK_SMALL),
    SMALL_ORANGE(ESize.SMALL, EColor.ORANGE, Features.BACKPACK_SMALL),
    SMALL_WHITE(ESize.SMALL, EColor.WHITE, Features.BACKPACK_SMALL),
    MEDIUM(ESize.MEDIUM, EColor.NONE, Features.BACKPACK_MEDIUM),
    MEDIUM_BLACK(ESize.MEDIUM, EColor.BLACK, Features.BACKPACK_MEDIUM),
    MEDIUM_RED(ESize.MEDIUM, EColor.RED, Features.BACKPACK_MEDIUM),
    MEDIUM_GREEN(ESize.MEDIUM, EColor.GREEN, Features.BACKPACK_MEDIUM),
    MEDIUM_BROWN(ESize.MEDIUM, EColor.BROWN, Features.BACKPACK_MEDIUM),
    MEDIUM_BLUE(ESize.MEDIUM, EColor.BLUE, Features.BACKPACK_MEDIUM),
    MEDIUM_PURPLE(ESize.MEDIUM, EColor.PURPLE, Features.BACKPACK_MEDIUM),
    MEDIUM_CYAN(ESize.MEDIUM, EColor.CYAN, Features.BACKPACK_MEDIUM),
    MEDIUM_LIGHT_GRAY(ESize.MEDIUM, EColor.LIGHT_GRAY, Features.BACKPACK_MEDIUM),
    MEDIUM_GRAY(ESize.MEDIUM, EColor.GRAY, Features.BACKPACK_MEDIUM),
    MEDIUM_PINK(ESize.MEDIUM, EColor.PINK, Features.BACKPACK_MEDIUM),
    MEDIUM_LIME(ESize.MEDIUM, EColor.LIME, Features.BACKPACK_MEDIUM),
    MEDIUM_YELLOW(ESize.MEDIUM, EColor.YELLOW, Features.BACKPACK_MEDIUM),
    MEDIUM_LIGHT_BLUE(ESize.MEDIUM, EColor.LIGHT_BLUE, Features.BACKPACK_MEDIUM),
    MEDIUM_MAGENTA(ESize.MEDIUM, EColor.MAGENTA, Features.BACKPACK_MEDIUM),
    MEDIUM_ORANGE(ESize.MEDIUM, EColor.ORANGE, Features.BACKPACK_MEDIUM),
    MEDIUM_WHITE(ESize.MEDIUM, EColor.WHITE, Features.BACKPACK_MEDIUM),
    BIG(ESize.BIG, EColor.NONE, Features.BACKPACK_BIG),
    BIG_BLACK(ESize.BIG, EColor.BLACK, Features.BACKPACK_BIG),
    BIG_RED(ESize.BIG, EColor.RED, Features.BACKPACK_BIG),
    BIG_GREEN(ESize.BIG, EColor.GREEN, Features.BACKPACK_BIG),
    BIG_BROWN(ESize.BIG, EColor.BROWN, Features.BACKPACK_BIG),
    BIG_BLUE(ESize.BIG, EColor.BLUE, Features.BACKPACK_BIG),
    BIG_PURPLE(ESize.BIG, EColor.PURPLE, Features.BACKPACK_BIG),
    BIG_CYAN(ESize.BIG, EColor.CYAN, Features.BACKPACK_BIG),
    BIG_LIGHT_GRAY(ESize.BIG, EColor.LIGHT_GRAY, Features.BACKPACK_BIG),
    BIG_GRAY(ESize.BIG, EColor.GRAY, Features.BACKPACK_BIG),
    BIG_PINK(ESize.BIG, EColor.PINK, Features.BACKPACK_BIG),
    BIG_LIME(ESize.BIG, EColor.LIME, Features.BACKPACK_BIG),
    BIG_YELLOW(ESize.BIG, EColor.YELLOW, Features.BACKPACK_BIG),
    BIG_LIGHT_BLUE(ESize.BIG, EColor.LIGHT_BLUE, Features.BACKPACK_BIG),
    BIG_MAGENTA(ESize.BIG, EColor.MAGENTA, Features.BACKPACK_BIG),
    BIG_ORANGE(ESize.BIG, EColor.ORANGE, Features.BACKPACK_BIG),
    BIG_WHITE(ESize.BIG, EColor.WHITE, Features.BACKPACK_BIG);

    private static Hashtable<Integer, String> VARIANTS = new Hashtable<Integer, String>();

    protected ESize size;
    protected EColor color;
    protected Features feature;
    protected String identifier;
    protected int damage;
    protected NBTTagCompound nbtTagCompound;

    EBackpack(ESize size, EColor color, Features feature) {
        this.size = size;
        this.color = color;
        this.feature = feature;

        identifier = size.name().toLowerCase();
        if (color.getName().length() > 0) {
            identifier += '_' + color.getName();
        }

        damage = size.getDamage() + color.getDamage();
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isEnabled() {
        return feature.isEnabled() && (color.equals(EColor.NONE) || Features.RECOLOR_BACKPACKS.isEnabled());
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(BackpackItems.backpack, 1, damage);

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
