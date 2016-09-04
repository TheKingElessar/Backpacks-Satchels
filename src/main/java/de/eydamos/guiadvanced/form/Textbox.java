package de.eydamos.guiadvanced.form;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import de.eydamos.guiadvanced.misc.AbstractGuiPart;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class Textbox extends GuiTextField implements AbstractGuiPart {
    protected int relativePositionX;
    protected int relativePositionY;

    public Textbox(FontRenderer fontRenderer, int posX, int posY, int width, int height) {
        super(0, fontRenderer, posX, posY, width, height);
        relativePositionX = posX;
        relativePositionY = posY;
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
    public void draw(Minecraft mc, int mouseX, int mouseY, float something) {
        drawTextBox();
    }

    @Override
    public void setAbsolutePosition(int guiLeft, int guiTop) {
        xPosition = guiLeft + relativePositionX;
        yPosition = guiTop + relativePositionY;
    }

}
