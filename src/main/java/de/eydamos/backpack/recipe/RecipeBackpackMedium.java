package de.eydamos.backpack.recipe;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.helper.ItemStackHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class RecipeBackpackMedium extends AbstractRecipe {
    public RecipeBackpackMedium(ItemStack result) {
        super(1, 2, result);
    }

    @Override
    protected boolean checkItemAtPosition(
        InventoryCrafting craftingGridInventory,
        int col,
        int row,
        int expectedCol,
        int expectedRow
    ) {
        // col and row are swapped in the method
        ItemStack itemStack = craftingGridInventory.getStackInRowAndColumn(col, row);

        if (expectedCol == 0 && expectedRow == 0 && !ItemStackHelper.isTopPiece(itemStack)) {
            return false;
        }

        if (expectedCol == 0 && expectedRow == 1) {
            if (!ItemStackHelper.isBottomPiece(itemStack)) {
                return false;
            }

            if (!ItemStackHelper.sameTier(itemStack, craftingGridInventory.getStackInRowAndColumn(col, row - 1))) {
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
