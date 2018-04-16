package de.eydamos.backpack;

import de.eydamos.backpack.init.BackpackItems;
import de.eydamos.backpack.init.Configurations;
import de.eydamos.backpack.item.*;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.EItemStack;
import de.eydamos.backpack.recipe.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.Map;

@EventBusSubscriber
public enum Features {
    STICK {
        @Override
        void addItems(IForgeRegistry<Item> registry) {
            stick = new ItemFunctionless("stick", 64, true);
            registry.register(stick);
        }

        @Override
        void addModel() {
            for (Map.Entry<Integer, String> variant : EStick.getVariants().entrySet()) {
                ModelLoader.setCustomModelResourceLocation(BackpackItems.stick, variant.getKey(), this.getResource(variant.getValue()));
            }
        }

        @Override
        public String getConfigComment() {
            return "Enable all stick recipes";
        }
    },
    STICK_STONE(STICK) {
        @Override
        void addOreDict() {
            OreDictionary.registerOre("stickStone", new ItemStack(stick, 1, EStick.STONE.getDamage()));
        }

        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "stick_stone", EItemStack.STICK_STONE.getItemStack(4), "S", "S", 'S', "stone");
        }

        @Override
        public String getConfigComment() {
            return "Enable stone stick recipe";
        }
    },
    STICK_IRON(STICK) {
        @Override
        void addOreDict() {
            OreDictionary.registerOre("stickIron", new ItemStack(stick, 1, EStick.IRON.getDamage()));
        }

        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "stick_iron", EItemStack.STICK_IRON.getItemStack(2), "S", "S", 'S', "ingotIron");
        }

        @Override
        public String getConfigComment() {
            return "Enable iron stick recipe";
        }
    },
    LEATHER_BOUND {
        @Override
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemFunctionless("bound_leather"));
        }

        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "bound_leather", new ItemStack(BackpackItems.bound_leather), "SSS", "LSL", "SSS", 'S', Items.STRING, 'L', Items.LEATHER);
        }

        @Override
        void addModel() {
            ModelLoader.setCustomModelResourceLocation(BackpackItems.bound_leather, 0, this.getResource(""));
        }

        @Override
        public String getConfigComment() {
            return "Enable bound leather recipe";
        }
    },
    LEATHER_TANNED {
        @Override
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemFunctionless("tanned_leather"));
        }

        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            GameRegistry.addSmelting(new ItemStack(BackpackItems.bound_leather), new ItemStack(BackpackItems.tanned_leather), 0.1f);
        }

        @Override
        void addModel() {
            ModelLoader.setCustomModelResourceLocation(BackpackItems.tanned_leather, 0, this.getResource(""));
        }

        @Override
        public String getConfigComment() {
            return "Enable tanned leather recipe";
        }
    },
    BACKPACK_FRAME {
        @Override
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemFunctionless("backpack_frame", 1, true));
        }

        @Override
        void addModel() {
            for (Map.Entry<Integer, String> variant : EFrame.getVariants().entrySet()) {
                ModelLoader.setCustomModelResourceLocation(BackpackItems.backpack_frame, variant.getKey(), this.getResource(variant.getValue()));
            }
        }

        @Override
        public String getConfigComment() {
            return "Enables all frame recipes";
        }
    },
    BACKPACK_FRAME_WOOD {
        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "backpack_frame_wood1", EItemStack.FRAME_WOOD.getItemStack(1), "WSW", "S S", "WSW", 'W', Items.STICK, 'S', Items.STRING);
            Features.addShapedRecipe(registry, "backpack_frame_wood2", EItemStack.FRAME_WOOD.getItemStack(1), "SWS", "W W", "SWS", 'W', Items.STICK, 'S', Items.STRING);
        }

        @Override
        public String getConfigComment() {
            return "Enable wooden frame recipe";
        }
    },
    BACKPACK_FRAME_STONE(STICK_STONE) {
        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "backpack_frame_stone1", EItemStack.FRAME_STONE.getItemStack(1), "WSW", "S S", "WSW", 'W', "stickStone", 'S', Items.STRING);
            Features.addShapedRecipe(registry, "backpack_frame_stone2", EItemStack.FRAME_STONE.getItemStack(1), "SWS", "W W", "SWS", 'W', "stickStone", 'S', Items.STRING);
        }

        @Override
        public String getConfigComment() {
            return "Enable stone frame recipe";
        }
    },
    BACKPACK_FRAME_IRON(STICK_IRON) {
        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "backpack_frame_iron1", EItemStack.FRAME_IRON.getItemStack(1), "WSW", "S S", "WSW", 'W', "stickIron", 'S', Items.STRING);
            Features.addShapedRecipe(registry, "backpack_frame_iron2", EItemStack.FRAME_IRON.getItemStack(1), "SWS", "W W", "SWS", 'W', "stickIron", 'S', Items.STRING);
        }

        @Override
        public String getConfigComment() {
            return "Enable iron frame recipe";
        }
    },
    BACKPACK_PIECE(BACKPACK_FRAME) {
        @Override
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemFunctionless("backpack_piece", 1, true));
        }

        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addRecipe(registry, "backpack_piece_top", new RecipeBackpackPieceTop(EItemStack.BACKPACK_PICE_TOP.getItemStack(1)));
            Features.addRecipe(registry, "backpack_piece_middle", new RecipeBackpackPieceMiddle(EItemStack.BACKPACK_PICE_MIDDLE.getItemStack(1)));
            Features.addRecipe(registry, "backpack_piece_bottom", new RecipeBackpackPieceBottom(EItemStack.BACKPACK_PICE_BOTTOM.getItemStack(1)));
        }

        @Override
        void addModel() {
            for (Map.Entry<Integer, String> variant : EPiece.getVariants().entrySet()) {
                ModelLoader.setCustomModelResourceLocation(BackpackItems.backpack_piece, variant.getKey(), this.getResource(variant.getValue()));
            }
        }

        @Override
        public String getConfigComment() {
            return "Enable backpack piece recipes";
        }
    },
    BACKPACK(BACKPACK_PIECE) {
        @Override
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemBackpack());
        }

        @Override
        void addModel() {
            for (Map.Entry<Integer, String> variant : EBackpack.getVariants().entrySet()) {
                ModelLoader.setCustomModelResourceLocation(BackpackItems.backpack, variant.getKey(), this.getResource(variant.getValue()));
            }
        }

        @Override
        public String getConfigComment() {
            return "Enable backpack recipes";
        }
    },
    BACKPACK_SMALL(BACKPACK) {
        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addRecipe(registry, "backpack_small", new RecipeBackpackSmall(EBackpack.SMALL.getItemStack()));
        }

        @Override
        public String getConfigComment() {
            return "Enable small backpack recipe";
        }
    },
    BACKPACK_MEDIUM(BACKPACK) {
        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addRecipe(registry, "backpack_medium", new RecipeBackpackMedium(EBackpack.MEDIUM.getItemStack()));
        }

        @Override
        public String getConfigComment() {
            return "Enable medium backpack recipe";
        }
    },
    BACKPACK_BIG(BACKPACK) {
        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addRecipe(registry, "backpack_big", new RecipeBackpackBig(EBackpack.BIG.getItemStack()));
        }

        @Override
        public String getConfigComment() {
            return "Enable big backpack recipe";
        }
    },
    RECOLOR_BACKPACKS(BACKPACK) {
        @Override
        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addRecipe(registry, "recolor_backpack", new RecipeRecolorBackpack());
        }

        @Override
        public String getConfigComment() {
            return "Enables colored backpack recipes";
        }
    };

    /** ==================== Events ==================== **/

    @SubscribeEvent
    public static void loadItems(RegistryEvent.Register<Item> event) {
        Backpack.logger.info("Loading items...");
        int num = 0;
        for (Features feature : values()) {
            feature.addItems(event.getRegistry());
            ++num;
            if (feature.isEnabled()) {
                feature.addOreDict();
            } else {
                logDisabled(feature);
            }
        }
        Backpack.logger.info(num + " item features loaded.");
    }

    @SubscribeEvent
    public static void loadRecipes(RegistryEvent.Register<IRecipe> event) {
        Backpack.logger.info("Loading recipes...");
        int num = 0;
        for (Features feature : values()) {
            if (feature.isEnabled()) {
                feature.addRecipes(event.getRegistry());
                ++num;
            } else {
                logDisabled(feature);
            }
        }
        Backpack.logger.info(num + " recipe features loaded.");
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        Backpack.logger.info("Registering models...");
        int num = 0;
        for (Features feature : values()) {
            feature.addModel();
            ++num;
        }
        Backpack.logger.info(num + " model features loaded.");
    }

    /** ==================== Features class ==================== **/

    private static final ResourceLocation RECIPE_GROUP = new ResourceLocation("", "");
    private static Item stick;
    private Features parent;

    Features() {
        this(null);
    }

    Features(Features parent) {
        this.parent = parent;
    }

    void addItems(IForgeRegistry<Item> registry) {
    }

    void addOreDict() {
    }

    void addRecipes(IForgeRegistry<IRecipe> registry) {
    }

    void addModel(){
    }

    public boolean isEnabled() {
        return Configurations.featureEnabled(this) && hasParentFeature();
    }

    public String getConfigComment() {
        return "";
    }

    private boolean hasParentFeature() {
        return parent == null || parent.isEnabled();
    }

    private static void addShapedRecipe(IForgeRegistry<IRecipe> registry, String name, @Nonnull ItemStack result, Object... ingredients) {
        registry.register(new ShapedOreRecipe(RECIPE_GROUP, result, ingredients).setRegistryName(Constants.MOD_ID, name));
    }

    private static void addRecipe(IForgeRegistry<IRecipe> registry, String name, AbstractRecipe recipe) {
        registry.register(recipe.setRegistryName(Constants.MOD_ID, name));
    }

    private static void logDisabled(Features feature) {
        if (!feature.hasParentFeature() && feature.parent != null) {
            Backpack.logger.info("Skipping feature {} as its parent feature {} was disabled.", Configurations.featureName(feature), Configurations.featureName(feature.parent));
        } else {
            Backpack.logger.info("Skipping feature {} as it was disabled in the config.", Configurations.featureName(feature));
        }
    }

    protected ModelResourceLocation getResource(String name) {
        String identifier = Constants.MOD_ID + ':' + name().toLowerCase();

        if (name.length() > 0) {
            identifier += '_' + name;
        }

        return new ModelResourceLocation(identifier, "inventory");
    }
}
