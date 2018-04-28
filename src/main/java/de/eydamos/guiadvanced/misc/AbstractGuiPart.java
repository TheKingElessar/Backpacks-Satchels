package de.eydamos.guiadvanced.misc;

public abstract class AbstractGuiPart implements GuiPartInterface, GuiInterface, GuiStateInterface {
    protected int xPosition;

    protected int yPosition;

    protected final int xOffset;

    protected final int yOffset;

    private int width;

    private int height;

    private boolean enabled = true;

    private boolean visible = true;

    public AbstractGuiPart(int posX, int posY) {
        xOffset = posX;
        yOffset = posY;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int value) {
        width = value;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int value) {
        height = value;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean value) {
        enabled = value;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean value) {
        visible = value;
    }

    @Override
    public void setAbsolutePosition(int guiLeft, int guiTop) {
        xPosition = guiLeft + xOffset;
        yPosition = guiTop + yOffset;
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        boolean inX = xPosition <= mouseX && mouseX <= xPosition + getWidth();
        boolean inY = yPosition <= mouseY && mouseY <= yPosition + getHeight();

        return inX && inY;
    }
}
