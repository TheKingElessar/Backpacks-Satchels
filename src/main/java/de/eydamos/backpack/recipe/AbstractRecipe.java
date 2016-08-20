package de.eydamos.backpack.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;

public abstract class AbstractRecipe implements IRecipe {
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
    protected int colOffset;
    /**
     * The row offset for the recipe.
     */
    protected int rowOffset;
    /**
     * Is a array of ItemStack that composes the recipe.
     */
    protected ItemStack[] recipeItems;
    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;
    /**
     * The identifier to be used when registering recipe in recipe sorter.
     */
    private final String recipeSorterIdentifier;

    public AbstractRecipe(int width, int height, ItemStack output, String identifier) {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeOutput = output;
        this.recipeSorterIdentifier = identifier;
    }

    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    public ItemStack[] getRemainingItems(InventoryCrafting p_179532_1_) {
        ItemStack[] aitemstack = new ItemStack[p_179532_1_.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i) {
            ItemStack itemstack = p_179532_1_.getStackInSlot(i);
            aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
        }

        return aitemstack;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting craftingGridInventory, World world) {
        for (colOffset = 0; colOffset <= craftingGridInventory.getWidth() - recipeWidth; ++colOffset) {
            for (rowOffset = 0; rowOffset <= craftingGridInventory.getHeight() - recipeHeight; ++rowOffset) {
                if (this.checkMatch(craftingGridInventory)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting craftingGridInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                int expectedCol = col - colOffset;
                int expectedRow = row - rowOffset;
                ItemStack itemStack = craftingGridInventory.getStackInRowAndColumn(col, row);

                if (expectedCol < 0 || expectedCol >= recipeWidth || expectedRow < 0 || expectedRow >= recipeHeight) {
                    if (itemStack == null) {
                        continue;
                    }

                    return false;
                }

                if (!checkItemAtPosition(craftingGridInventory, col, row, expectedCol, expectedRow)) {
                    return false;
                }
            }
        }

        return true;
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
     * @return True if the ItemStack should be at this position, false otherwise.
     */
    abstract protected boolean checkItemAtPosition(InventoryCrafting craftingGridInventory, int col, int row, int expectedCol, int expectedRow);

    public void registerAtRecipeSorter() {
        RecipeSorter.register(recipeSorterIdentifier, this.getClass(), RecipeSorter.Category.SHAPED, "");
    }
}
