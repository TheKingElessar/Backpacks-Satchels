package de.eydamos.backpack.misc;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BackpackItems {
    public static Item bound_leather;
    public static Item tanned_leather;
    public static Item stick;
    public static Item backpack_frame;
    public static Item backpack_piece;
    public static Item backpack;

    private static Item getRegisteredItem(String identifier) {
        return (Item) Item.itemRegistry.getObject(new ResourceLocation(Constants.DOMAIN, identifier));
    }

    static {
        if (!Bootstrap.isRegistered()) {
            throw new RuntimeException("Accessed Items before Bootstrap!");
        } else {
            bound_leather = getRegisteredItem("bound_leather");
            tanned_leather = getRegisteredItem("tanned_leather");
            stick = getRegisteredItem("stick");
            backpack_frame = getRegisteredItem("backpack_frame");
            backpack_piece = getRegisteredItem("backpack_piece");
            backpack = getRegisteredItem("backpack");
        }
    }
}
