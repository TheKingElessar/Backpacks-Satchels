package de.eydamos.backpack.tier;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.eydamos.backpack.item.EFrame;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import de.eydamos.backpack.util.NBTItemStackUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

import java.util.List;

public enum TierFrame {
    I(EFrame.WOOD.getDamage()),
    II(EFrame.STONE.getDamage()),
    III(EFrame.IRON.getDamage());

    private final Item item = BackpackItems.backpack_frame;
    private final int damage;

    TierFrame(int damage) {
        this.damage = damage;
    }

    public Item getItem() {
        return item;
    }

    public int getDamage() {
        return damage;
    }

    public void setTier(ItemStack itemStack) {
        NBTItemStackUtil.setString(itemStack, Constants.NBT.FRAME_TIER, name());
    }

    public static TierFrame getTierByItemStack(ItemStack itemStack) {
        String tierName = NBTItemStackUtil.getString(itemStack, Constants.NBT.FRAME_TIER);
        if (!tierName.isEmpty()) {
            return TierFrame.valueOf(tierName);
        }

        Item item = itemStack.getItem();

        for (TierFrame tier : values()) {
            if (tier.item == item && itemStack.getItemDamage() == tier.damage) {
                return tier;
            }
        }

        return null;
    }

    public static void setTier(ItemStack target, ItemStack source) {
        TierFrame tier = getTierByItemStack(source);
        if (tier != null) {
            tier.setTier(target);
        }
    }

    public static boolean itemStackTierEquals(ItemStack itemStackA, ItemStack itemStackB) {
        TierFrame tierA = getTierByItemStack(itemStackA);
        TierFrame tierB = getTierByItemStack(itemStackB);

        return tierA != null && tierA.equals(tierB);
    }

    public static void addTooltip(ItemStack itemStack, List<String> tooltip) {
        TierFrame tier = getTierByItemStack(itemStack);

        if (tier != null) {
            String label = ChatFormatting.BLUE + I18n.translateToLocal(Localizations.TOOLTIP_FRAME_TIER);
            String tierName = ChatFormatting.YELLOW + tier.name() + ChatFormatting.RESET;
            tooltip.add(label.trim() + ' ' + tierName);
        }
    }
}
