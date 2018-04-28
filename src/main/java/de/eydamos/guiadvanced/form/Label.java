package de.eydamos.guiadvanced.form;

import de.eydamos.guiadvanced.misc.AbstractGuiPart;
import de.eydamos.guiadvanced.util.Alignment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class Label extends AbstractGuiPart {
    private int color;

    protected String text;

    private Alignment hAlignment;

    private Alignment vAlignment;

    public Label(int posX, int posY, String text) {
        this(posX, posY, text, 0x404040, Alignment.LEFT, Alignment.TOP);
    }

    public Label(int posX, int posY, String text, int color) {
        this(posX, posY, text, color, Alignment.LEFT, Alignment.TOP);
    }

    public Label(int posX, int posY, String text, int color, Alignment hAlignment) {
        this(posX, posY, text, color, hAlignment, Alignment.TOP);
    }

    public Label(int posX, int posY, String text, int color, Alignment hAlignment, Alignment vAlignment) {
        super(posX, posY);
        this.text = text;
        setColor(color);
        setHorizontalAlignment(hAlignment);
        setVerticalAlignment(vAlignment);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int value) {
        color = value;
    }

    public Alignment getHorizontalAlignment() {
        return hAlignment;
    }

    public void setHorizontalAlignment(Alignment value) {
        hAlignment = value;
    }

    public Alignment getVerticalAlignment() {
        return vAlignment;
    }

    public void setVerticalAlignment(Alignment vAlignment) {
        this.vAlignment = vAlignment;
    }

    @Override
    public void drawBackgroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
        if (!isVisible()) {
            return;
        }

        String label = I18n.format(text);
        int xOffset = 0;
        int yOffset = 0;

        switch (getHorizontalAlignment()) {
            case RIGHT:
                xOffset = getWidth() - getStringWidth(mc, label);
                break;
            case CENTER:
                xOffset = getWidth() / 2 - getStringWidth(mc, label) / 2;
                break;
        }

        switch (getVerticalAlignment()) {
            case CENTER:
                yOffset = getHeight() / 2 - getStringHeight(mc) / 2;
                break;
            case BOTTOM:
                yOffset = getHeight() - getStringHeight(mc);
                break;
        }

        mc.fontRenderer.drawString(label, xPosition + xOffset, yPosition + yOffset, getColor());
    }

    private int getStringWidth(Minecraft mc, String text) {
        return mc.fontRenderer.getStringWidth(text);
    }

    private int getStringHeight(Minecraft mc) {
        return mc.fontRenderer.FONT_HEIGHT;
    }
}
