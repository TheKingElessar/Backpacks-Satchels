package de.eydamos.backpack.jei.wrapper;

import de.eydamos.backpack.init.BackpackItems;
import de.eydamos.backpack.item.EPiece;
import de.eydamos.backpack.misc.EItemStack;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public class RecipeBackpackPieceMiddleWrapper implements IShapedCraftingRecipeWrapper {
    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 3;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getIngredients(IIngredients ingredients) {
        List<List<ItemStack>> recipeList = new ArrayList<>();

        ArrayList<ItemStack> leather = new ArrayList<>();
        leather.add(new ItemStack(Items.RABBIT_HIDE, 1, 0));
        leather.add(new ItemStack(Items.LEATHER, 1, 0));
        leather.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));

        ArrayList<ItemStack> backpack = new ArrayList<>();
        backpack.add(EItemStack.getItemStack(BackpackItems.backpack_frame, 1, OreDictionary.WILDCARD_VALUE));

        // top row
        recipeList.add(null);
        recipeList.add(leather);
        recipeList.add(null);
        // middle row
        recipeList.add(leather);
        recipeList.add(backpack);
        recipeList.add(leather);
        // bottom row
        recipeList.add(null);
        recipeList.add(leather);
        recipeList.add(null);

        ingredients.setInputLists(ItemStack.class, recipeList);
        ingredients.setOutput(ItemStack.class, EItemStack.getItemStack(BackpackItems.backpack_piece, 1, EPiece.MIDDLE.getDamage()));
    }
}
