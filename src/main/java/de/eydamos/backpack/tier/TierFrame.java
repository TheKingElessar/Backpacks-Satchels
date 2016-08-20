package de.eydamos.backpack.tier;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.eydamos.backpack.item.EFrame;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localitations;
import de.eydamos.backpack.util.NBTItemStackUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    public static TierFrame getTierByItemStack(@NotNull ItemStack itemStack) {
        Item item = itemStack.getItem();

        for (TierFrame tier : values()) {
            if (tier.item == item && itemStack.getItemDamage() == tier.damage) {
                return tier;
            }
        }

        String tierName = NBTItemStackUtil.getString(itemStack, Constants.NBT.FRAME_TIER);
        if (!tierName.isEmpty()) {
            return TierFrame.valueOf(tierName);
        }

        return null;
    }

    public static String getTextByItemStack(@NotNull ItemStack itemStack) {
        TierFrame tier = getTierByItemStack(itemStack);

        if (tier != null) {
            return tier.name();
        }

        return "";
    }

    public static void setTier(ItemStack target, ItemStack source) {
        TierFrame tier = getTierByItemStack(source);
        if (tier != null) {
            NBTItemStackUtil.setString(target, Constants.NBT.FRAME_TIER, tier.name());
        }
    }

    public static boolean itemStackTierEquals(ItemStack itemStackA, ItemStack itemStackB) {
        TierFrame tierA = getTierByItemStack(itemStackA);
        TierFrame tierB = getTierByItemStack(itemStackB);

        return tierA != null && tierA.equals(tierB);
    }

    public static void addTooltip(ItemStack itemStack, List<String> tooltip) {
        String tierName = getTextByItemStack(itemStack);

        if (!tierName.isEmpty()) {
            String label = ChatFormatting.BLUE + StatCollector.translateToLocal(Localitations.TOOLTIP_FRAME_TIER);
            tierName = ChatFormatting.YELLOW + tierName + ChatFormatting.RESET;
            tooltip.add(label.trim() + ' ' + tierName);
        }
    }
}
