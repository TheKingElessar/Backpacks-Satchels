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

public class RecipeBackpackPieceBottomWrapper implements IShapedCraftingRecipeWrapper {
    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getIngredients(IIngredients ingredients) {
        List<List<ItemStack>> inputs = new ArrayList<>();

        ArrayList<ItemStack> leather = new ArrayList<>();
        leather.add(new ItemStack(Items.RABBIT_HIDE, 1, 0));
        leather.add(new ItemStack(Items.LEATHER, 1, 0));
        leather.add(EItemStack.getItemStack(BackpackItems.tanned_leather, 1, 0));

        ArrayList<ItemStack> backpack = new ArrayList<>();
        backpack.add(EItemStack.getItemStack(BackpackItems.backpack_frame, 1, OreDictionary.WILDCARD_VALUE));

        // middle row
        inputs.add(leather);
        inputs.add(backpack);
        inputs.add(leather);
        // bottom row
        inputs.add(leather);
        inputs.add(leather);
        inputs.add(leather);

        ingredients.setInputLists(ItemStack.class, inputs);
        ingredients.setOutput(ItemStack.class, EItemStack.getItemStack(BackpackItems.backpack_piece, 1, EPiece.BOTTOM.getDamage()));
    }
}
