package de.eydamos.backpack.recipe;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.helper.HelperItems;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class RecipeBackpackMedium extends AbstractRecipe {
    public RecipeBackpackMedium(ItemStack result) {
        super(1, 2, result, Constants.RECIPE_BACKPACK_MEDIUM);
    }

    @Override
    protected boolean checkItemAtPosition(InventoryCrafting craftingGridInventory, int col, int row, int expectedCol, int expectedRow) {
        // col and row are swapped in the method
        ItemStack itemStack = craftingGridInventory.getStackInRowAndColumn(col, row);

        if (expectedCol == 0 && expectedRow == 0 && !HelperItems.isTopPiece(itemStack)) {
            return false;
        }

        if (expectedCol == 0 && expectedRow == 1) {
            if (!HelperItems.isBottomPiece(itemStack)) {
                return false;
            }

            if (!HelperItems.sameTier(itemStack, craftingGridInventory.getStackInRowAndColumn(col, row - 1))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftingGridInventory) {
        ItemStack result = this.getRecipeOutput().copy();
        ItemStack leather = craftingGridInventory.getStackInRowAndColumn(0 + colOffset, 0 + rowOffset);
        ItemStack frame = craftingGridInventory.getStackInRowAndColumn(0 + colOffset, 0 + rowOffset);

        BackpackHelper.initialize(result, leather, frame);

        return result;
    }
}
