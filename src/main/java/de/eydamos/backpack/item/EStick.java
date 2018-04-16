package de.eydamos.backpack.item;

import de.eydamos.backpack.Features;

import java.util.Hashtable;

public enum EStick {
    STONE(0, "stone", Features.STICK_STONE),
    IRON(1, "iron", Features.STICK_IRON);

    private static Hashtable<Integer, String> VARIANTS = new Hashtable<Integer, String>();

    private final int damage;
    private final String identifier;
    private final Features feature;

    EStick(int damage, String identifier, Features feature) {
        this.damage = damage;
        this.identifier = identifier;
        this.feature = feature;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isEnabled() {
        return feature.isEnabled();
    }

    public static String getIdentifierByDamage(int damage) {
        for (EStick stick : values()) {
            if (stick.getDamage() == damage) {
                return stick.identifier;
            }
        }

        return "";
    }

    public static Hashtable<Integer, String> getVariants() {
        return VARIANTS;
    }

    static {
        for (EStick stick : values()) {
            VARIANTS.put(stick.getDamage(), stick.getIdentifier());
        }
    }
}
