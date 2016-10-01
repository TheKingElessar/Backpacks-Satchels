package de.eydamos.backpack.misc;

import de.eydamos.backpack.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

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

            registerOreDictNames();

            alreadyRegistered = true;
        }
    }

    private static void registerItems() {
        for (Items item : Items.values()) {
            GameRegistry.register(item.getItem());
        }
    }

    private static void registerOreDictNames() {
        OreDictionary.registerOre("stickStone", new ItemStack(Items.STICK.getItem(), 1, EStick.STONE.getDamage()));
        OreDictionary.registerOre("stickIron", new ItemStack(Items.STICK.getItem(), 1, EStick.IRON.getDamage()));
    }

    @SideOnly(Side.CLIENT)
    public static void registerVariants() {
        for (Items item : Items.values()) {
            Hashtable<Integer, String> variants = item.getVariants();

            if (variants == null) {
                continue;
            }

            for (String variant : variants.values()) {
                String identifier = Constants.MOD_ID + ':' + item.getUnlocalizedName() + '_' + variant;
                ModelBakery.registerItemVariants(item.getItem(), item.getResource(identifier));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerIcons() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        for (Items item : Items.values()) {
            String identifier = Constants.MOD_ID + ':' + item.getUnlocalizedName();
            Hashtable<Integer, String> variants = item.getVariants();

            if (variants == null) {
                mesher.register(item.getItem(), 0, item.getResource(identifier));
                continue;
            }

            for (Entry<Integer, String> variant : variants.entrySet()) {
                String variantIdentifier = identifier + '_' + variant.getValue();
                mesher.register(item.getItem(), variant.getKey(), item.getResource(variantIdentifier));
            }
        }
    }

    private enum Items {
        BACKPACK("backpack", ItemBackpack.class, 1, true, EBackpack.getVariants()),
        BOUND_LEATHER("bound_leather", ItemFunctionless.class, 64, false, null),
        TANNED_LEATHER("tanned_leather", ItemFunctionless.class, 64, false, null),
        STICK("stick", ItemFunctionless.class, 64, true, EStick.getVariants()),
        BACKPACK_FRAME("backpack_frame", ItemFunctionless.class, 1, true, EFrame.getVariants()),
        BACKPACK_PIECE("backpack_piece", ItemFunctionless.class, 1, true, EPiece.getVariants());

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

        public ModelResourceLocation getResource(String identifier) {
            return new ModelResourceLocation(identifier, "inventory");
        }
    }
}
