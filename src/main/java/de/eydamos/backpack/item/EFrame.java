package de.eydamos.backpack.item;

import java.util.Hashtable;

public enum EFrame {
    WOOD(0, "wood"),
    STONE(1, "stone"),
    IRON(2, "iron");

    private static Hashtable<Integer, String> VARIANTS = new Hashtable<Integer, String>();

    private final int damage;
    private final String identifier;

    EFrame(int damage, String identifier) {
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
        for (EFrame frame : values()) {
            if (frame.getDamage() == damage) {
                return frame.identifier;
            }
        }

        return "";
    }

    public static Hashtable<Integer, String> getVariants() {
        return VARIANTS;
    }

    static {
        for (EFrame frame : values()) {
            VARIANTS.put(frame.getDamage(), frame.getIdentifier());
        }
    }
}
