package de.eydamos.guiadvanced.config;

public enum State {
    ENABLED,
    DISABLED,
    HOVER;

    public String getIdentifier() {
        return name().toLowerCase();
    }
}
