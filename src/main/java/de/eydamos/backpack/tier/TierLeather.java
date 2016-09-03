package de.eydamos.backpack.tier;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import de.eydamos.backpack.util.NBTItemStackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public enum TierLeather {
    I(Items.RABBIT_HIDE, 6, 3),
    II(Items.LEATHER, 12, 6),
    III(BackpackItems.tanned_leather, 18, 9);

    private final Item item;
    private final int baseSlots;
    private final int upgradeSlots;

    TierLeather(Item item, int baseSlots, int upgradeSlots) {
        this.item = item;
        this.baseSlots = baseSlots;
        this.upgradeSlots = upgradeSlots;
    }

    public Item getItem() {
        return item;
    }

    public int getBaseSlots() {
        return baseSlots;
    }

    public int getUpgradeSlots() {
        return upgradeSlots;
    }

    public void setTier(ItemStack itemStack) {
        NBTItemStackUtil.setString(itemStack, Constants.NBT.LEATHER_TIER, name());
    }

    public static TierLeather getTierByItemStack(ItemStack itemStack) {
        String tierName = NBTItemStackUtil.getString(itemStack, Constants.NBT.LEATHER_TIER);
        if (!tierName.isEmpty()) {
            return TierLeather.valueOf(tierName);
        }

        Item item = itemStack.getItem();

        for (TierLeather tier : values()) {
            if (tier.item == item) {
                return tier;
            }
        }

        return null;
    }

    public static void setTier(ItemStack target, ItemStack source) {
        TierLeather tier = getTierByItemStack(source);

        if (tier != null) {
            tier.setTier(target);
        }
    }

    public static boolean itemStackTierEquals(ItemStack itemStackA, ItemStack itemStackB) {
        TierLeather tierA = getTierByItemStack(itemStackA);
        TierLeather tierB = getTierByItemStack(itemStackB);

        return tierA != null && tierA.equals(tierB);
    }

    public static void addTooltip(ItemStack itemStack, List<String> tooltip) {
        TierLeather tier = getTierByItemStack(itemStack);

        if (tier != null) {
            String label = ChatFormatting.BLUE + I18n.format(Localizations.TOOLTIP_LEATHER_TIER);
            String tierName = ChatFormatting.YELLOW + tier.name() + ChatFormatting.RESET;
            tooltip.add(label.trim() + ' ' + tierName);
        }
    }
}
