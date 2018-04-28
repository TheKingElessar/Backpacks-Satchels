package de.eydamos.guiadvanced.config;

import com.google.common.base.CaseFormat;

public enum Fragment {
    OUTER_CORNER__TOP_LEFT(Variant.TOP_LEFT, true),
    OUTER_CORNER__TOP_RIGHT(Variant.TOP_RIGHT, true),
    OUTER_CORNER__BOTTOM_RIGHT(Variant.BOTTOM_RIGHT, true),
    OUTER_CORNER__BOTTOM_LEFT(Variant.BOTTOM_LEFT, true),
    INNER_CORNER__TOP_LEFT(Variant.TOP_LEFT, true),
    INNER_CORNER__TOP_RIGHT(Variant.TOP_RIGHT, true),
    INNER_CORNER__BOTTOM_RIGHT(Variant.BOTTOM_RIGHT, true),
    INNER_CORNER__BOTTOM_LEFT(Variant.BOTTOM_LEFT, true),
    OUTER_BORDER__TOP(Variant.TOP),
    OUTER_BORDER__RIGHT(Variant.RIGHT),
    OUTER_BORDER__BOTTOM(Variant.BOTTOM),
    OUTER_BORDER__LEFT(Variant.LEFT),
    INNER_BORDER__TOP(Variant.TOP),
    INNER_BORDER__RIGHT(Variant.RIGHT),
    INNER_BORDER__BOTTOM(Variant.BOTTOM),
    INNER_BORDER__LEFT(Variant.LEFT),
    BACKGROUND(Variant.DEFAULT);

    private final Variant variant;

    private final boolean corner;

    Fragment(Variant variant) {
        this(variant, false);
    }

    Fragment(Variant variant, boolean corner) {
        this.variant = variant;
        this.corner = corner;
    }

    public String getKey() {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name().split("__")[0]);
    }

    public Variant getVariant() {
        return variant;
    }

    public boolean isCorner() {
        return corner;
    }
}
