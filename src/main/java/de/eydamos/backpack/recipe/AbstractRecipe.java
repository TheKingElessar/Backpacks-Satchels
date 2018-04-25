package de.eydamos.backpack.recipe;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class AbstractRecipe extends Impl<IRecipe> implements IRecipe {
    /**
     * How many horizontal slots this recipe is wide.
     */
    protected final int recipeWidth;

    /**
     * How many vertical slots this recipe uses.
     */
    protected final int recipeHeight;

    /**
     * The column offset for the recipe.
     */
    protected int colOffset = 0;

    /**
     * The row offset for the recipe.
     */
    protected int rowOffset = 0;

    /**
     * Is a array of ItemStack that composes the recipe.
     */
    protected ItemStack[] recipeItems;

    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;

    /**
     * The type of the recipe (Shaped / Shapeless)
     */
    private final ECategory type;

    public AbstractRecipe(int width, int height, ItemStack output) {
        this(width, height, output, ECategory.SHAPED);
    }

    public AbstractRecipe(int width, int height, ItemStack output, ECategory type) {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeOutput = output;
        this.type = type;

        if (type == ECategory.CUSTOM || type == ECategory.FURNACE) {
            throw new IllegalArgumentException("Only shaped and shapeless types are allowed");
        }
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= this.recipeWidth && height >= this.recipeHeight;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting craftingGridInventory) {
        NonNullList<ItemStack> remainingItems = NonNullList.create();

        for (int i = 0; i < craftingGridInventory.getSizeInventory(); ++i) {
            ItemStack itemstack = craftingGridInventory.getStackInSlot(i);
            remainingItems.add(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }

        return remainingItems;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @ParametersAreNonnullByDefault
    public boolean matches(InventoryCrafting craftingGridInventory, World world) {
        if (type == ECategory.SHAPED || type == ECategory.SHAPED_OREDICT) {
            for (colOffset = 0; colOffset <= craftingGridInventory.getWidth() - recipeWidth; ++colOffset) {
                for (rowOffset = 0; rowOffset <= craftingGridInventory.getHeight() - recipeHeight; ++rowOffset) {
                    if (this.checkMatch(craftingGridInventory)) {
                        return true;
                    }
                }
            }
        }

        if (type == ECategory.SHAPELESS || type == ECategory.SHAPELESS_OREDICT) {
            boolean breakLoop = false;
            for (int row = 0; row < 3; ++row) {
                for (int col = 0; col < 3; ++col) {
                    if (!checkItemAtPosition(craftingGridInventory, col, row, 0, 0)) {
                        breakLoop = true;
                        break;
                    }
                }

                if (breakLoop) {
                    break;
                }
            }

            return allRecipeItemsFulfilled();
        }

        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting craftingGridInventory) {
        boolean emptyGrid = true;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                int expectedCol = col - colOffset;
                int expectedRow = row - rowOffset;
                ItemStack itemStack = craftingGridInventory.getStackInRowAndColumn(col, row);

                if (expectedCol < 0 || expectedCol >= recipeWidth || expectedRow < 0 || expectedRow >= recipeHeight) {
                    if (itemStack.isEmpty()) {
                        continue;
                    }

                    return false;
                }

                emptyGrid = false;

                if (!checkItemAtPosition(craftingGridInventory, col, row, expectedCol, expectedRow)) {
                    return false;
                }
            }
        }

        return !emptyGrid;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize() {
        return this.recipeWidth * this.recipeHeight;
    }

    /**
     * This method checks if the ItemStack at the given column and row is the one that the recipe needs at the expected
     * column and row.
     *
     * @param craftingGridInventory The craftingInventory to check.
     * @param col                   The current column in the crafting grid.
     * @param row                   The current row in the crafting grid.
     * @param expectedCol           The expected column in the recipe.
     * @param expectedRow           The expected row in the recipe.
     *
     * @return True if the ItemStack should be at this position, false otherwise.
     */
    abstract protected boolean checkItemAtPosition(
        InventoryCrafting craftingGridInventory,
        int col,
        int row,
        int expectedCol,
        int expectedRow
    );

    protected boolean allRecipeItemsFulfilled() {
        return true;
    }
}
