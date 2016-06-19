package de.eydamos.backpack.misc;

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
}
