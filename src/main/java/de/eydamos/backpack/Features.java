package de.eydamos.backpack;

import de.eydamos.backpack.init.BackpackItems;
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
        void addItems(IForgeRegistry<Item> registry) {
            Item stick = new ItemFunctionless("stick", 64, true);
            registry.register(stick);

            OreDictionary.registerOre("stickStone", new ItemStack(stick, 1, EStick.STONE.getDamage()));
            OreDictionary.registerOre("stickIron", new ItemStack(stick, 1, EStick.IRON.getDamage()));
        }

        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "stick_stone", EItemStack.STICK_STONE.getItemStack(4), "S", "S", 'S', "stone");
            Features.addShapedRecipe(registry, "stick_iron", EItemStack.STICK_IRON.getItemStack(2), "S", "S", 'S', "ingotIron");
        }

        void addModel() {
            for (Map.Entry<Integer, String> variant : EStick.getVariants().entrySet()) {
                ModelLoader.setCustomModelResourceLocation(BackpackItems.stick, variant.getKey(), this.getResource(variant.getValue()));
            }
        }
    },
    LEATHER_BOUND {
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemFunctionless("bound_leather"));
        }

        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "bound_leather", new ItemStack(BackpackItems.bound_leather), "SSS", "LSL", "SSS", 'S', Items.STRING, 'L', Items.LEATHER);
        }

        void addModel() {
            ModelLoader.setCustomModelResourceLocation(BackpackItems.bound_leather, 0, this.getResource(""));
        }
    },
    LEATHER_TANNED {
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemFunctionless("tanned_leather"));
        }

        void addRecipes(IForgeRegistry<IRecipe> registry) {
            GameRegistry.addSmelting(new ItemStack(BackpackItems.bound_leather), new ItemStack(BackpackItems.tanned_leather), 0.1f);
        }

        void addModel() {
            ModelLoader.setCustomModelResourceLocation(BackpackItems.tanned_leather, 0, this.getResource(""));
        }
    },
    BACKPACK_FRAME {
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemFunctionless("backpack_frame", 1, true));
        }

        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addShapedRecipe(registry, "backpack_frame_wood1", EItemStack.FRAME_WOOD.getItemStack(1), "WSW", "S S", "WSW", 'W', Items.STICK, 'S', Items.STRING);
            Features.addShapedRecipe(registry, "backpack_frame_wood2", EItemStack.FRAME_WOOD.getItemStack(1), "SWS", "W W", "SWS", 'W', Items.STICK, 'S', Items.STRING);
            Features.addShapedRecipe(registry, "backpack_frame_stone1", EItemStack.FRAME_STONE.getItemStack(1), "WSW", "S S", "WSW", 'W', "stickStone", 'S', Items.STRING);
            Features.addShapedRecipe(registry, "backpack_frame_stone2", EItemStack.FRAME_STONE.getItemStack(1), "SWS", "W W", "SWS", 'W', "stickStone", 'S', Items.STRING);
            Features.addShapedRecipe(registry, "backpack_frame_iron1", EItemStack.FRAME_IRON.getItemStack(1), "WSW", "S S", "WSW", 'W', "stickIron", 'S', Items.STRING);
            Features.addShapedRecipe(registry, "backpack_frame_iron2", EItemStack.FRAME_IRON.getItemStack(1), "SWS", "W W", "SWS", 'W', "stickIron", 'S', Items.STRING);
        }

        void addModel() {
            for (Map.Entry<Integer, String> variant : EFrame.getVariants().entrySet()) {
                ModelLoader.setCustomModelResourceLocation(BackpackItems.backpack_frame, variant.getKey(), this.getResource(variant.getValue()));
            }
        }
    },
    BACKPACK_PIECE {
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemFunctionless("backpack_piece", 1, true));
        }

        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addRecipe(registry, "backpack_piece_top", new RecipeBackpackPieceTop(EItemStack.BACKPACK_PICE_TOP.getItemStack(1)));
            Features.addRecipe(registry, "backpack_piece_middle", new RecipeBackpackPieceMiddle(EItemStack.BACKPACK_PICE_MIDDLE.getItemStack(1)));
            Features.addRecipe(registry, "backpack_piece_bottom", new RecipeBackpackPieceBottom(EItemStack.BACKPACK_PICE_BOTTOM.getItemStack(1)));
        }

        void addModel() {
            for (Map.Entry<Integer, String> variant : EPiece.getVariants().entrySet()) {
                ModelLoader.setCustomModelResourceLocation(BackpackItems.backpack_piece, variant.getKey(), this.getResource(variant.getValue()));
            }
        }
    },
    BACKPACK {
        void addItems(IForgeRegistry<Item> registry) {
            registry.register(new ItemBackpack());
        }

        void addRecipes(IForgeRegistry<IRecipe> registry) {
            Features.addRecipe(registry, "backpack_small", new RecipeBackpackSmall(EBackpack.SMALL.getItemStack()));
            Features.addRecipe(registry, "backpack_medium", new RecipeBackpackMedium(EBackpack.MEDIUM.getItemStack()));
            Features.addRecipe(registry, "backpack_big", new RecipeBackpackBig(EBackpack.BIG.getItemStack()));
            Features.addRecipe(registry, "recolor_backpack", new RecipeRecolorBackpack());
        }

        void addModel() {
            for (Map.Entry<Integer, String> variant : EBackpack.getVariants().entrySet()) {
                ModelLoader.setCustomModelResourceLocation(BackpackItems.backpack, variant.getKey(), this.getResource(variant.getValue()));
            }
        }
    };

    @SubscribeEvent
    public static void loadItems(RegistryEvent.Register<Item> event) {
        Backpack.logger.info("Loading items...");
        int num = 0;
        for (Features feature : values()) {
            if (feature.enabled()) {
                feature.addItems(event.getRegistry());
                ++num;
            } else {
                Backpack.logger.info("Skipping feature {} as it was disabled in the config.", feature.name());
            }
        }
        Backpack.logger.info(num + " items loaded.");
    }

    @SubscribeEvent
    public static void loadRecipes(RegistryEvent.Register<IRecipe> event) {
        Backpack.logger.info("Loading recipes...");
        int num = 0;
        for (Features feature : values()) {
            if (feature.enabled()) {
                feature.addRecipes(event.getRegistry());
                ++num;
            } else {
                Backpack.logger.info("Skipping feature {} as it was disabled in the config.", feature.name());
            }
        }
        Backpack.logger.info(num + " recipes loaded.");
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        Backpack.logger.info("Registering models...");
        int num = 0;
        for (Features feature : values()) {
            if (feature.enabled()) {
                feature.addModel();
                ++num;
            } else {
                Backpack.logger.info("Skipping feature {} as it was disabled in the config.", feature.name());
            }
        }
        Backpack.logger.info(num + " models loaded.");
    }

    private static final ResourceLocation RECIPE_GROUP = new ResourceLocation("", "");

    void addItems(IForgeRegistry<Item> registry) {
    }

    void addRecipes(IForgeRegistry<IRecipe> registry) {
    }

    void addModel(){
    }

    public boolean enabled() {
        // TODO implement logic with config
        return true;
    }

    private static void addShapedRecipe(IForgeRegistry<IRecipe> registry, String name, @Nonnull ItemStack result, Object... ingredients) {
        name = "backpack:" + name;
        registry.register(new ShapedOreRecipe(RECIPE_GROUP, result, ingredients).setRegistryName(Constants.MOD_ID, name));
    }

    private static void addRecipe(IForgeRegistry<IRecipe> registry, String name, AbstractRecipe recipe) {
        name = "backpack:" + name;
        registry.register(recipe.setRegistryName(Constants.MOD_ID, name));
    }

    protected ModelResourceLocation getResource(String name) {
        String identifier = Constants.MOD_ID + ':' + name().toLowerCase();

        if (name.length() > 0) {
            identifier += '_' + name;
        }

        return new ModelResourceLocation(identifier, "inventory");
    }
}
