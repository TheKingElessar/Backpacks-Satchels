package de.eydamos.backpack.item;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public enum ESize {
    SMALL(0),
    MEDIUM(100),
    BIG(200);

    protected int damage;

    ESize(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Nullable
    public static ESize getSizeByBackpack(@NotNull ItemStack itemStack) {
        int sizeDamage = itemStack.getItemDamage() / 100 * 100;

        for (ESize size : values()) {
            if (sizeDamage == size.getDamage()) {
                return size;
            }
        }

        return null;
    }
}
