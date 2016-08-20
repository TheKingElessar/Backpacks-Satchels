package de.eydamos.backpack.item;

import java.util.Hashtable;

public enum EStick {
    STONE(0, "stone"),
    IRON(1, "iron");

    private static Hashtable<Integer, String> VARIANTS = new Hashtable<Integer, String>();

    private final int damage;
    private final String identifier;

    EStick(int damage, String identifier) {
        this.damage = damage;
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getDamage() {
        return damage;
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
