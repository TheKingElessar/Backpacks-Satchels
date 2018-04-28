package de.eydamos.guiadvanced.config;

import com.google.common.base.CaseFormat;

public enum Variant {
    TOP_LEFT("size", "size"),
    TOP_RIGHT("size", "size"),
    BOTTOM_RIGHT("size", "size"),
    BOTTOM_LEFT("size", "size"),
    TOP("length", "thickness"),
    RIGHT("thickness", "length"),
    BOTTOM("length", "thickness"),
    LEFT("thickness", "length"),
    DEFAULT("width", "height");

    private final String widthKey;

    private final String heightKey;

    Variant(String widthKey, String heightKey) {
        this.widthKey = widthKey;
        this.heightKey = heightKey;
    }

    public String getKey() {
        return getKey("");
    }

    public String getKey(String suffix) {
        if (this.equals(DEFAULT)) {
            return "";
        }

        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name()) + suffix;
    }

    public String getWidthKey() {
        return widthKey;
    }

    public String getHeightKey() {
        return heightKey;
    }
}
