package de.eydamos.backpack.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public enum EBackpack {
    SMALL(ESize.SMALL, EColor.NONE),
    SMALL_BLACK(ESize.SMALL, EColor.BLACK),
    SMALL_RED(ESize.SMALL, EColor.RED),
    SMALL_GREEN(ESize.SMALL, EColor.GREEN),
    SMALL_BROWN(ESize.SMALL, EColor.BROWN),
    SMALL_BLUE(ESize.SMALL, EColor.BLUE),
    SMALL_PURPLE(ESize.SMALL, EColor.PURPLE),
    SMALL_CYAN(ESize.SMALL, EColor.CYAN),
    SMALL_LIGHT_GRAY(ESize.SMALL, EColor.LIGHT_GRAY),
    SMALL_GRAY(ESize.SMALL, EColor.GRAY),
    SMALL_PINK(ESize.SMALL, EColor.PINK),
    SMALL_LIME(ESize.SMALL, EColor.LIME),
    SMALL_YELLOW(ESize.SMALL, EColor.YELLOW),
    SMALL_LIGHT_BLUE(ESize.SMALL, EColor.LIGHT_BLUE),
    SMALL_MAGENTA(ESize.SMALL, EColor.MAGENTA),
    SMALL_ORANGE(ESize.SMALL, EColor.ORANGE),
    SMALL_WHITE(ESize.SMALL, EColor.WHITE),
    MEDIUM(ESize.MEDIUM, EColor.NONE),
    MEDIUM_BLACK(ESize.MEDIUM, EColor.BLACK),
    MEDIUM_RED(ESize.MEDIUM, EColor.RED),
    MEDIUM_GREEN(ESize.MEDIUM, EColor.GREEN),
    MEDIUM_BROWN(ESize.MEDIUM, EColor.BROWN),
    MEDIUM_BLUE(ESize.MEDIUM, EColor.BLUE),
    MEDIUM_PURPLE(ESize.MEDIUM, EColor.PURPLE),
    MEDIUM_CYAN(ESize.MEDIUM, EColor.CYAN),
    MEDIUM_LIGHT_GRAY(ESize.MEDIUM, EColor.LIGHT_GRAY),
    MEDIUM_GRAY(ESize.MEDIUM, EColor.GRAY),
    MEDIUM_PINK(ESize.MEDIUM, EColor.PINK),
    MEDIUM_LIME(ESize.MEDIUM, EColor.LIME),
    MEDIUM_YELLOW(ESize.MEDIUM, EColor.YELLOW),
    MEDIUM_LIGHT_BLUE(ESize.MEDIUM, EColor.LIGHT_BLUE),
    MEDIUM_MAGENTA(ESize.MEDIUM, EColor.MAGENTA),
    MEDIUM_ORANGE(ESize.MEDIUM, EColor.ORANGE),
    MEDIUM_WHITE(ESize.MEDIUM, EColor.WHITE),
    BIG(ESize.BIG, EColor.NONE),
    BIG_BLACK(ESize.BIG, EColor.BLACK),
    BIG_RED(ESize.BIG, EColor.RED),
    BIG_GREEN(ESize.BIG, EColor.GREEN),
    BIG_BROWN(ESize.BIG, EColor.BROWN),
    BIG_BLUE(ESize.BIG, EColor.BLUE),
    BIG_PURPLE(ESize.BIG, EColor.PURPLE),
    BIG_CYAN(ESize.BIG, EColor.CYAN),
    BIG_LIGHT_GRAY(ESize.BIG, EColor.LIGHT_GRAY),
    BIG_GRAY(ESize.BIG, EColor.GRAY),
    BIG_PINK(ESize.BIG, EColor.PINK),
    BIG_LIME(ESize.BIG, EColor.LIME),
    BIG_YELLOW(ESize.BIG, EColor.YELLOW),
    BIG_LIGHT_BLUE(ESize.BIG, EColor.LIGHT_BLUE),
    BIG_MAGENTA(ESize.BIG, EColor.MAGENTA),
    BIG_ORANGE(ESize.BIG, EColor.ORANGE),
    BIG_WHITE(ESize.BIG, EColor.WHITE);

    protected ESize size;
    protected EColor color;
    protected String identifier;
    protected int damage;
    protected Constants.Items item = Constants.Items.BACKPACK;
    protected NBTTagCompound nbtTagCompound;

    EBackpack(ESize size, EColor color) {
        this.size = size;
        this.color = color;

        identifier = "backpack_";
        identifier += size.name().toLowerCase();
        if (color.getName().length() > 0) {
            identifier += '_' + color.getName();
        }

        damage = size.getDamage() + color.getDamage();

        this.nbtTagCompound = new NBTTagCompound();

        this.nbtTagCompound.setString("unlocalized", identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getDamage() {
        return damage;
    }

    public void registerItem() {
        if (item.getItem() == null) {
            GameRegistry.registerItem(item.setItem(1, true), item.getUnlocalizedName());
            ModelBakery.addVariantName(item.getItem(), Constants.MOD_ID + ':' + identifier);
        } else {
            ModelBakery.addVariantName(item.getItem(), Constants.MOD_ID + ':' + identifier);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcon() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        // identifier must be the same as the filename of the json
        mesher.register(item.getItem(), damage, new ModelResourceLocation(Constants.MOD_ID + ':' + identifier, "inventory"));
    }

    public ItemStack getItemStack(int amount) {
        ItemStack itemStack = new ItemStack(item.getItem(), amount, damage);

        itemStack.setTagCompound(this.nbtTagCompound);

        return itemStack;
    }

    public static void registerItems() {
        for (EBackpack backpack : values()) {
            backpack.registerItem();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerIcons() {
        for (EBackpack backpack : values()) {
            backpack.registerIcon();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void addSubItem(List<ItemStack> subItems) {
        for (EBackpack backpack : values()) {
            ItemStack itemStack = new ItemStack(backpack.item.getItem(), 1, backpack.getDamage());
            itemStack.setTagInfo("unlocalized", new NBTTagString(backpack.getIdentifier()));
            subItems.add(itemStack);
        }
    }
}
