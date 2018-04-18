package de.eydamos.guiadvanced.form;

import de.eydamos.guiadvanced.misc.AbstractGuiPart;
import de.eydamos.guiadvanced.util.Rectangle;
import de.eydamos.guiadvanced.util.RenderHelper.BackgroundRepeat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import javax.annotation.ParametersAreNonnullByDefault;

public class Button extends GuiButton implements AbstractGuiPart {
    protected int relativePositionX;

    protected int relativePositionY;

    public Button(int buttonId, int posX, int posY, int width, int height, String text) {
        super(buttonId, posX, posY, width, height, text);
        relativePositionX = posX;
        relativePositionY = posY;
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
    public void draw(Minecraft mc, int mouseX, int mouseY, float something) {
        // unneeded GuiScreen calls drawButton directly so we overwrite this method
    }

    @Override
    public void setAbsolutePosition(int guiLeft, int guiTop) {
        x = guiLeft + relativePositionX;
        y = guiTop + relativePositionY;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float something) {
        if (visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
            int offset = getHoverState(hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
            );
            GlStateManager.blendFunc(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA
            );

            Rectangle rectangle = new Rectangle(2, 2);
            rectangle.setBackground(BUTTON_TEXTURES);
            rectangle.setBackgroundSize(2, 2);
            // draw upper left corner
            rectangle.setBackgroundPosition(0, 46 + offset * 20);
            rectangle.draw(x, y);
            // draw upper right corner
            rectangle.setBackgroundPosition(198, 46 + offset * 20);
            rectangle.draw(x + width - 2, y);
            // draw lower left corner
            rectangle.setBackgroundPosition(0, 64 + offset * 20);
            rectangle.draw(x, y + height - 2);
            // draw lower right corner
            rectangle.setBackgroundPosition(198, 64 + offset * 20);
            rectangle.draw(x + width - 2, y + height - 2);

            // borders top/bottom
            rectangle.setWidth(width - 4);
            rectangle.setBackgroundRepeat(BackgroundRepeat.REPEAT_X);
            // draw top border
            rectangle.setBackgroundPosition(2, 46 + offset * 20);
            rectangle.draw(x + 2, y);
            // draw bottom border
            rectangle.setBackgroundPosition(2, 64 + offset * 20);
            rectangle.draw(x + 2, y + height - 2);

            // borders left/right
            rectangle.setWidth(2);
            rectangle.setHeight(height - 4);
            rectangle.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
            // draw left border
            rectangle.setBackgroundPosition(0, 48 + offset * 20);
            rectangle.draw(x, y + 2);
            // draw right border
            rectangle.setBackgroundPosition(198, 48 + offset * 20);
            rectangle.draw(x + width - 2, y + 2);

            // draw background
            rectangle.setWidth(width - 4);
            rectangle.setHeight(height - 4);
            rectangle.setBackgroundSize(18, 18);
            rectangle.setBackgroundRepeat(BackgroundRepeat.REPEAT);
            rectangle.setBackgroundPosition(2, 48 + offset * 20);
            rectangle.draw(x + 2, y + 2);

            mouseDragged(mc, mouseX, mouseY);
            int l = 14737632;

            if (packedFGColour != 0) {
                l = packedFGColour;
            } else if (!enabled) {
                l = 10526880;
            } else if (hovered) {
                l = 16777120;
            }

            drawCenteredString(fontrenderer, displayString, x + width / 2 + 1, y + (height - 8) / 2, l);
        }
    }
}
