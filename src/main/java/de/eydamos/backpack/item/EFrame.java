package de.eydamos.backpack.item;

import de.eydamos.backpack.Features;

import java.util.Hashtable;

public enum EFrame {
    WOOD(0, "wood", Features.BACKPACK_FRAME_WOOD),
    STONE(1, "stone", Features.BACKPACK_FRAME_STONE),
    IRON(2, "iron", Features.BACKPACK_FRAME_IRON);

    private static Hashtable<Integer, String> VARIANTS = new Hashtable<>();

    private final int damage;

    private final String identifier;

    private final Features feature;

    EFrame(int damage, String identifier, Features feature) {
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
