package de.eydamos.backpack.recipe;

import de.eydamos.backpack.misc.EItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public enum ERecipe {
    STICK_STONE(EItem.STICK_STONE, 4, ECategory.SHAPED_OREDICT, "S", "S", 'S', "stone"),
    STICK_IRON(EItem.STICK_IRON, 2, ECategory.SHAPED_OREDICT, "S", "S", 'S', "ingotIron"),
    LEATHER_BOUND(EItem.LEATHER_BOUND, 2, ECategory.SHAPED, "SSS", "LSL", "SSS", 'S', Items.string, 'L', Items.leather),
    LEATHER_TANNED(EItem.LEATHER_TANNED, 1, ECategory.FURNACE, EItem.LEATHER_BOUND.getItemStack(1), 0.1f),
    FRAME_WOOD(EItem.FRAME_WOOD, 1, ECategory.SHAPED, "WSW", "S S", "WSW", 'W', Items.stick, 'S', Items.string),
    FRAME_WOOD2(EItem.FRAME_WOOD, 1, ECategory.SHAPED, "SWS", "W W", "SWS", 'W', Items.stick, 'S', Items.string),
    FRAME_STONE(EItem.FRAME_STONE, 1, ECategory.SHAPED, "WSW", "S S", "WSW", 'W', EItem.STICK_STONE.getItemStack(1), 'S', Items.string),
    FRAME_STONE2(EItem.FRAME_STONE, 1, ECategory.SHAPED, "SWS", "W W", "SWS", 'W', EItem.STICK_STONE.getItemStack(1), 'S', Items.string),
    FRAME_IRON(EItem.FRAME_IRON, 1, ECategory.SHAPED, "WSW", "S S", "WSW", 'W', EItem.STICK_IRON.getItemStack(1), 'S', Items.string),
    FRAME_IRON2(EItem.FRAME_IRON, 1, ECategory.SHAPED, "SWS", "W W", "SWS", 'W', EItem.STICK_IRON.getItemStack(1), 'S', Items.string);

    protected EItem result;
    protected int resultAmount;
    protected ECategory category;
    protected Object[] recipe;

    ERecipe(EItem result, int resultAmount, ECategory category, Object... recipe) {
        this.result = result;
        this.resultAmount = resultAmount;
        this.category = category;
        this.recipe = recipe;
    }

    public void registerRecipe() {
        ItemStack itemStack = result.getItemStack(resultAmount);
        switch (category) {
            case SHAPED:
                GameRegistry.addShapedRecipe(itemStack, recipe);
                break;
            case SHAPED_OREDICT:
                GameRegistry.addRecipe(new ShapedOreRecipe(itemStack, recipe));
                break;
            case SHAPELESS:
                GameRegistry.addShapelessRecipe(itemStack, recipe);
                break;
            case SHAPELESS_OREDICT:
                GameRegistry.addRecipe(new ShapelessOreRecipe(itemStack, recipe));
                break;
            case FURNACE:
                GameRegistry.addSmelting((ItemStack) recipe[0], itemStack, (Float) recipe[1]);
                break;
        }
    }

    public static void registerRecipes() {
        for (ERecipe recipe : values()) {
            recipe.registerRecipe();
        }
    }
}
