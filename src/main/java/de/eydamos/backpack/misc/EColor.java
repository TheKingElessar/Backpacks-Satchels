package de.eydamos.backpack.misc;

public enum EColor {
    NONE(0, ""),
    BLACK(1, "black"),
    RED(2, "red"),
    GREEN(3, "green"),
    BROWN(4, "brown"),
    BLUE(5, "blue"),
    PURPLE(6, "purple"),
    CYAN(7, "cyan"),
    LIGHT_GRAY(8, "lightGray"),
    GRAY(9, "gray"),
    PINK(10, "pink"),
    LIME(11, "lime"),
    YELLOW(12, "yellow"),
    LIGHT_BLUE(13, "lightBlue"),
    MAGENTA(14, "magenta"),
    ORANGE(15, "orange"),
    WHITE(16, "white");

    protected int damage;
    protected String name;

    EColor(int damage, String name) {
        this.damage = damage;
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}
