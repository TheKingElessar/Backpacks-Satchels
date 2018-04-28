package de.eydamos.guiadvanced.misc;

public interface GuiInterface {
    int getWidth();

    void setWidth(int value);

    int getHeight();

    void setHeight(int value);

    boolean isMouseOver(int mouseX, int mouseY);
}
