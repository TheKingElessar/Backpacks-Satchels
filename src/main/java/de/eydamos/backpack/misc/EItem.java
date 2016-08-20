package de.eydamos.backpack.misc;

import de.eydamos.backpack.item.ItemFunctionless;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EItem {
    STICK_STONE(Constants.Items.STICK, "stick_stone", 64, true, 0),
    STICK_IRON(Constants.Items.STICK, "stick_iron", 64, true, 1),
    LEATHER_BOUND(Constants.Items.LEATHER, "leather_bound", 64, true, 0),
    LEATHER_TANNED(Constants.Items.LEATHER, "leather_tanned", 64, true, 1),
    FRAME_WOOD(Constants.Items.FRAME, "frame_wood", 1, true, 0, Constants.NBT.FRAME_TIER, "I"),
    FRAME_STONE(Constants.Items.FRAME, "frame_stone", 1, true, 1, Constants.NBT.FRAME_TIER, "II"),
    FRAME_IRON(Constants.Items.FRAME, "frame_iron", 1, true, 2, Constants.NBT.FRAME_TIER, "III"),
    BACKPACK_PICE_TOP(Constants.Items.BACKPACK_PICE, "backpack_piece_top", 1, true, 0),
    BACKPACK_PICE_MIDDLE(Constants.Items.BACKPACK_PICE, "backpack_piece_middle", 1, true, 1),
    BACKPACK_PICE_BOTTOM(Constants.Items.BACKPACK_PICE, "backpack_piece_bottom", 1, true, 2);

    protected Constants.Items item;
    protected String unlocalizedName;
    protected int stackSize;
    protected boolean hasSubTypes;
    protected int damage;
    protected NBTTagCompound nbtTagCompound;

    EItem(Constants.Items item, String unlocalizedName, int stackSize, boolean hasSubTypes, int damage, Object... nbtData) {
        this.item = item;
        this.unlocalizedName = unlocalizedName;
        this.stackSize = stackSize;
        this.hasSubTypes = hasSubTypes;
        this.damage = damage;
        this.nbtTagCompound = new NBTTagCompound();

        for (int i = 0; i < nbtData.length; i += 2) {
            String key = String.valueOf(nbtData[i]);
            String value = String.valueOf(nbtData[i + 1]);

            nbtTagCompound.setString(key, value);
        }
    }

    public void registerItem() {
        if (item.getItem() == null) {
            GameRegistry.registerItem(item.setItem(stackSize, hasSubTypes), item.getUnlocalizedName());
            ModelBakery.addVariantName(item.getItem(), Constants.MOD_ID + ':' + unlocalizedName);
            ((ItemFunctionless) item.getItem()).addSubItem(damage, unlocalizedName);
        } else {
            ModelBakery.addVariantName(item.getItem(), Constants.MOD_ID + ':' + unlocalizedName);
            ((ItemFunctionless) item.getItem()).addSubItem(damage, unlocalizedName);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcon() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        // identifier must be the same as the filename of the json
        mesher.register(item.getItem(), damage, new ModelResourceLocation(Constants.MOD_ID + ':' + unlocalizedName, "inventory"));
    }

    public ItemStack getItemStack(int amount) {
        ItemStack itemStack = new ItemStack(item.getItem(), amount, damage);

        itemStack.setTagCompound(this.nbtTagCompound);

        return itemStack;
    }

    public static void registerItems() {
        for (EItem item : values()) {
            item.registerItem();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerIcons() {
        for (EItem item : values()) {
            item.registerIcon();
        }
    }
}
