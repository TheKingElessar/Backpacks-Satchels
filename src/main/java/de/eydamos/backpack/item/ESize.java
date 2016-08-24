package de.eydamos.backpack.item;

import net.minecraft.item.ItemStack;

public enum ESize {
    SMALL(0, 1, 1),
    MEDIUM(100, 2, 3),
    BIG(200, 3, 6);

    protected final int damage;
    protected final int slotMultiplier;
    protected final int moduleSlots;

    ESize(int damage, int slotMultiplier, int moduleSlots) {
        this.damage = damage;
        this.slotMultiplier = slotMultiplier;
        this.moduleSlots = moduleSlots;
    }

    public int getDamage() {
        return damage;
    }

    public int getSlotMultiplier() {
        return slotMultiplier;
    }

    public int getModuleSlots() {
        return moduleSlots;
    }

    public static ESize getSizeByBackpack(ItemStack itemStack) {
        int sizeDamage = itemStack.getItemDamage() / 100 * 100;

        for (ESize size : values()) {
            if (sizeDamage == size.getDamage()) {
                return size;
            }
        }

        return null;
    }
}
