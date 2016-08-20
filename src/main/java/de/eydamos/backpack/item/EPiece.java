package de.eydamos.backpack.item;

import java.util.Hashtable;

public enum EPiece {
    TOP(0, "top"),
    MIDDLE(1, "middle"),
    BOTTOM(2, "bottom");

    private static Hashtable<Integer, String> VARIANTS = new Hashtable<Integer, String>();

    private final int damage;
    private final String identifier;

    EPiece(int damage, String identifier) {
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
        for (EPiece piece : values()) {
            if (piece.getDamage() == damage) {
                return piece.identifier;
            }
        }

        return "";
    }

    public static Hashtable<Integer, String> getVariants() {
        return VARIANTS;
    }

    static {
        for (EPiece piece : values()) {
            VARIANTS.put(piece.getDamage(), piece.getIdentifier());
        }
    }
}
