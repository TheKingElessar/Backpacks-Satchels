package de.eydamos.guiadvanced.subpart;

import de.eydamos.guiadvanced.misc.AbstractGuiPart;
import de.eydamos.guiadvanced.util.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Icon extends AbstractGuiPart {
    private final int uPosition;

    private final int vPosition;

    private final ResourceLocation image;

    public Icon(int posX, int posY, int widthHeight) {
        this(posX, posY, widthHeight, widthHeight, 0, 0, null);
    }

    public Icon(int posX, int posY, int width, int height) {
        this(posX, posY, width, height, 0, 0, null);
    }

    public Icon(int posX, int posY, int width, int height, int u, int v) {
        this(posX, posY, width, height, u, v, null);
    }

    public Icon(int posX, int posY, int width, int height, ResourceLocation graphic) {
        this(posX, posY, width, height, 0, 0, graphic);
    }

    public Icon(int posX, int posY, int width, int height, int u, int v, ResourceLocation graphic) {
        super(posX, posY);
        setWidth(width);
        setHeight(height);
        image = graphic;
        uPosition = u;
        vPosition = v;
    }

    @Override
    public void drawBackgroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
        if (!isVisible()) {
            return;
        }

        Rectangle icon = new Rectangle(getWidth(), getHeight());
        if (image != null) {
            icon.setBackground(image);
        }
        icon.setBackgroundPosition(uPosition, vPosition);
        icon.setBackgroundSize(getWidth(), getHeight());
        icon.draw(xPosition, yPosition);
    }
}
