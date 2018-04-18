package de.eydamos.backpack.recipe;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.helper.ItemStackHelper;
import de.eydamos.backpack.init.BackpackItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class RecipeBackpackSmall extends AbstractRecipe {
    public RecipeBackpackSmall(ItemStack result) {
        super(3, 3, result);
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
        int iteratorPosition = expectedCol + expectedRow * recipeWidth;
        switch (iteratorPosition) {
            case 4:
                if (itemStack.getItem() != BackpackItems.backpack_frame) {
                    return false;
                }

                break;
            default:
                if (!ItemStackHelper.isLeather(itemStack)) {
                    return false;
                }

                if (iteratorPosition != 0) {
                    int firstLeatherPosition = col + row * craftingGridInventory.getWidth() - iteratorPosition;
                    ItemStack firstLeather = craftingGridInventory.getStackInSlot(firstLeatherPosition);
                    if (!ItemStackHelper.isSameLeatherType(itemStack, firstLeather)) {
                        return false;
                    }
                }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftingGridInventory) {
        ItemStack result = this.getRecipeOutput().copy();
        ItemStack leather = craftingGridInventory.getStackInRowAndColumn(0 + colOffset, 0 + rowOffset);
        ItemStack frame = craftingGridInventory.getStackInRowAndColumn(1 + colOffset, 1 + rowOffset);

        BackpackHelper.initialize(result, leather, frame);

        return result;
    }
}
