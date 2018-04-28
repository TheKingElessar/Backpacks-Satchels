package de.eydamos.guiadvanced.misc;

import net.minecraft.client.Minecraft;

public interface GuiPartInterface extends GuiStateInterface {
    default void drawBackgroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
    }

    default void drawForegroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
    }

    default void drawTooltips(Minecraft mc, int mouseX, int mouseY, float something) {
    }

    void setAbsolutePosition(int guiLeft, int guiTop);

    default void onMouseDown(int mouseX, int mouseY, int mouseButton) {
    }

    default void onMouseUp(int mouseX, int mouseY, int state) {
    }

    default void onKeyDown(char typedChar, int keyCode) {
    }
}
