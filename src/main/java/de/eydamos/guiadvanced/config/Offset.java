package de.eydamos.guiadvanced.config;

public class Offset {
    private final int u;

    private final int v;

    public Offset() {
        this(0, 0);
    }

    public Offset(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }
}
