package de.eydamos.guiadvanced.misc;

import java.util.List;

public interface WindowInterface extends GuiInterface {
    void drawHoveringText(String text, int x, int y);

    void drawHoveringText(List<String> textLines, int x, int y);

    void disableAllPartHolders();
}
