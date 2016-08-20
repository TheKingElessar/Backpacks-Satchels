package de.eydamos.backpack.misc;

import de.eydamos.backpack.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Hashtable;
import java.util.Map.Entry;

public class Bootstrap {
    private static boolean alreadyRegistered = false;

    public static boolean isRegistered() {
        return alreadyRegistered;
    }

    public static void register() {
        if (!alreadyRegistered) {
            registerItems();

            alreadyRegistered = true;
        }
    }

    private static void registerItems() {
        for (Items item : Items.values()) {
            GameRegistry.registerItem(item.getItem(), item.getUnlocalizedName());
            Hashtable<Integer, String> variants = item.getVariants();

            if (variants == null) {
                continue;
            }

            for (String variant : variants.values()) {
                String identifier = Constants.MOD_ID + ':' + item.getUnlocalizedName() + '_' + variant;
                if (variant.contains("medium") || identifier.contains("frame")) { continue; }
                ModelBakery.addVariantName(item.getItem(), identifier);
            }
        }
    }

    public static void registerIcons() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        for (Items item : Items.values()) {
            String identifier = Constants.MOD_ID + ':' + item.getUnlocalizedName();
            Hashtable<Integer, String> variants = item.getVariants();

            if (variants == null) {
                mesher.register(item.getItem(), 0, new ModelResourceLocation(identifier, "inventory"));
                continue;
            }

            for (Entry<Integer, String> variant : variants.entrySet()) {
                String variantIdentifier = identifier + '_' + variant.getValue();
                if (variantIdentifier.contains("medium") || variantIdentifier.contains("frame")) { continue; }
                mesher.register(item.getItem(), variant.getKey(), new ModelResourceLocation(variantIdentifier, "inventory"));
            }
        }
    }

    private enum Items {
        BOUND_LEATHER("bound_leather", ItemFunctionless.class, 64, false, null),
        TANNED_LEATHER("tanned_leather", ItemFunctionless.class, 64, false, null),
        STICK("stick", ItemFunctionless.class, 64, true, EStick.getVariants()),
        BACKPACK_FRAME("backpack_frame", ItemFunctionless.class, 1, true, EFrame.getVariants()),
        BACKPACK_PIECE("backpack_piece", ItemFunctionless.class, 1, true, EPiece.getVariants()),
        BACKPACK("backpack", ItemBackpack.class, 1, true, EBackpack.getVariants());

        private Item item;
        private final Class clazz;
        private final String unlocalizedName;
        private final int stackSize;
        private final boolean hasSubTypes;
        private final Hashtable<Integer, String> variants;

        Items(String unlocalizedName, Class clazz, int stackSize, boolean hasSubTypes, Hashtable<Integer, String> variants) {
            this.unlocalizedName = unlocalizedName;
            this.clazz = clazz;
            this.stackSize = stackSize;
            this.hasSubTypes = hasSubTypes;
            this.variants = variants;
        }

        public Item getItem() {
            if (item == null) {
                if (clazz == ItemFunctionless.class) {
                    item = new ItemFunctionless(unlocalizedName, stackSize, hasSubTypes);
                } else if (clazz == ItemBackpack.class) {
                    item = new ItemBackpack();
                    EBackpack.setItem(item);
                }
            }

            return item;
        }

        public String getUnlocalizedName() {
            return unlocalizedName;
        }

        public Hashtable<Integer, String> getVariants() {
            return variants;
        }
    }
}
