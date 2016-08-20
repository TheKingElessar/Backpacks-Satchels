package de.eydamos.backpack.recipe;

import de.eydamos.backpack.helper.HelperItems;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class RecipeBackpackPieceBottom extends AbstractRecipe {
    public RecipeBackpackPieceBottom(ItemStack result) {
        super(3, 2, result, Constants.RECIPE_BACKPACK_PIECE_BOTTOM);
    }

    @Override
    protected boolean checkItemAtPosition(InventoryCrafting craftingGridInventory, int col, int row, int expectedCol, int expectedRow) {
        // col and row are swapped in the method
        ItemStack itemStack = craftingGridInventory.getStackInRowAndColumn(col, row);
        int iteratorPosition = expectedCol + expectedRow * recipeWidth;
        switch (iteratorPosition) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                if (!HelperItems.isLeather(itemStack)) {
                    return false;
                }

                if (iteratorPosition != 0) {
                    int firstLeatherPosition = col + row * craftingGridInventory.getWidth() - iteratorPosition;
                    ItemStack firstLeather = craftingGridInventory.getStackInSlot(firstLeatherPosition);
                    if (!HelperItems.isSameLeatherType(itemStack, firstLeather)) {
                        return false;
                    }
                }

                break;
            case 1:
                if (itemStack == null || itemStack.getItem() != BackpackItems.backpack_frame) {
                    return false;
                }

                break;
            default:
                if (itemStack != null) {
                    return false;
                }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftingGridInventory) {
        ItemStack result = this.getRecipeOutput().copy();
        ItemStack leather = craftingGridInventory.getStackInRowAndColumn(0 + colOffset, 0 + rowOffset);
        ItemStack frame = craftingGridInventory.getStackInRowAndColumn(1 + colOffset, 0 + rowOffset);

        TierLeather.setTier(result, leather);
        TierFrame.setTier(result, frame);

        return result;
    }
}
