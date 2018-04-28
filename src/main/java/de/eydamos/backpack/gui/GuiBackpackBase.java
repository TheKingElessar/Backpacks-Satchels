package de.eydamos.backpack.gui;

import de.eydamos.backpack.handler.HandlerInputEvents;
import de.eydamos.backpack.storage.container.ContainerAdvanced;
import de.eydamos.guiadvanced.window.ContainerWindow;

import java.io.IOException;

public class GuiBackpackBase extends ContainerWindow {
    public GuiBackpackBase(ContainerAdvanced container) {
        super(container);
        setWidth(container.getWidth());
        setHeight(container.getHeight());
    }

    @Override
    protected void keyTyped(char charTyped, int keyCode) throws IOException {
        super.keyTyped(charTyped, keyCode);

        if (HandlerInputEvents.personalBackpack.getKeyCode() == keyCode) {
            mc.player.closeScreen();
        }
    }
}
