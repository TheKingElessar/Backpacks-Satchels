package de.eydamos.backpack.tier;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import de.eydamos.backpack.util.NBTItemStackUtil;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public enum TierLeather {
    I(Items.rabbit_hide),
    II(Items.leather),
    III(BackpackItems.tanned_leather);

    private final Item item;

    TierLeather(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public static TierLeather getTierByItemStack(@NotNull ItemStack itemStack) {
        return getTierByItemStack(itemStack, false);
    }

    @Nullable
    public static TierLeather getTierByItemStack(@NotNull ItemStack itemStack, boolean strict) {
        Item item = itemStack.getItem();

        for (TierLeather tier : values()) {
            if (tier.item == item) {
                return tier;
            }
        }

        String tierName = NBTItemStackUtil.getString(itemStack, Constants.NBT.LEATHER_TIER);
        if (!strict && !tierName.isEmpty()) {
            return TierLeather.valueOf(tierName);
        }

        return null;
    }

    public static String getTextByItemStack(@NotNull ItemStack itemStack) {
        TierLeather tier = getTierByItemStack(itemStack);

        if (tier != null) {
            return tier.name();
        }

        return "";
    }

    public static void setTier(ItemStack target, ItemStack source) {
        TierLeather tier = getTierByItemStack(source);
        if (tier != null) {
            NBTItemStackUtil.setString(target, Constants.NBT.LEATHER_TIER, tier.name());
        }
    }

    public static boolean itemStackTierEquals(ItemStack itemStackA, ItemStack itemStackB) {
        TierLeather tierA = getTierByItemStack(itemStackA);
        TierLeather tierB = getTierByItemStack(itemStackB);

        return tierA != null && tierA.equals(tierB);
    }

    public static void addTooltip(ItemStack itemStack, List<String> tooltip) {
        String tierName = getTextByItemStack(itemStack);

        if (!tierName.isEmpty()) {
            String label = ChatFormatting.BLUE + StatCollector.translateToLocal(Localizations.TOOLTIP_LEATHER_TIER);
            tierName = ChatFormatting.YELLOW + tierName + ChatFormatting.RESET;
            tooltip.add(label.trim() + ' ' + tierName);
        }
    }
}
