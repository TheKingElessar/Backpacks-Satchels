package de.eydamos.guiadvanced.form;

import de.eydamos.guiadvanced.misc.GuiInterface;
import de.eydamos.guiadvanced.misc.GuiPartInterface;
import de.eydamos.guiadvanced.misc.GuiStateInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class Textbox extends GuiTextField implements GuiPartInterface, GuiInterface, GuiStateInterface {
    private final int xOffset;

    private final int yOffset;

    public Textbox(FontRenderer fontRenderer, int posX, int posY, int width, int height) {
        super(0, fontRenderer, posX, posY, width, height);
        xOffset = posX;
        yOffset = posY;
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
        return (boolean) ReflectionHelper.getPrivateValue(GuiTextField.class, this, "isEnabled", "field_146226_p");
    }

    @Override
    public boolean isVisible() {
        return getVisible();
    }

    @Override
    public void drawBackgroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
        drawTextBox();
    }

    @Override
    public void setAbsolutePosition(int guiLeft, int guiTop) {
        x = guiLeft + xOffset;
        y = guiTop + yOffset;
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        boolean inX = x <= mouseX && mouseX <= x + getWidth();
        boolean inY = y <= mouseY && mouseY <= y + getHeight();

        return inX && inY;
    }
}
