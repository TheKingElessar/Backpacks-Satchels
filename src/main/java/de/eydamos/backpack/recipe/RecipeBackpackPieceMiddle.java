package de.eydamos.backpack.recipe;

import de.eydamos.backpack.helper.ItemStackHelper;
import de.eydamos.backpack.init.BackpackItems;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class RecipeBackpackPieceMiddle extends AbstractRecipe {
    public RecipeBackpackPieceMiddle(ItemStack result) {
        super(3, 3, result);
    }

    @Override
    protected boolean checkItemAtPosition(InventoryCrafting craftingGridInventory, int col, int row, int expectedCol, int expectedRow) {
        // col and row are swapped in the method
        ItemStack itemStack = craftingGridInventory.getStackInRowAndColumn(col, row);
        int iteratorPosition = expectedCol + expectedRow * recipeWidth;
        switch (iteratorPosition) {
            case 1:
            case 3:
            case 5:
            case 7:
                if (!ItemStackHelper.isLeather(itemStack)) {
                    return false;
                }

                if (iteratorPosition != 1) {
                    int firstLeatherPosition = col + row * craftingGridInventory.getWidth() - iteratorPosition + 1;
                    ItemStack firstLeather = craftingGridInventory.getStackInSlot(firstLeatherPosition);
                    if (!ItemStackHelper.isSameLeatherType(itemStack, firstLeather)) {
                        return false;
                    }
                }

                break;
            case 4:
                if (itemStack.getItem() != BackpackItems.backpack_frame) {
                    return false;
                }

                break;
            default:
                if (!itemStack.isEmpty()) {
                    return false;
                }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftingGridInventory) {
        ItemStack result = this.getRecipeOutput().copy();
        ItemStack leather = craftingGridInventory.getStackInRowAndColumn(1 + colOffset, 0 + rowOffset);
        ItemStack frame = craftingGridInventory.getStackInRowAndColumn(1 + colOffset, 1 + rowOffset);

        TierLeather.setTier(result, leather);
        TierFrame.setTier(result, frame);

        return result;
    }
}
