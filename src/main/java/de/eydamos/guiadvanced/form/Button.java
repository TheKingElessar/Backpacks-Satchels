package de.eydamos.guiadvanced.form;

import de.eydamos.guiadvanced.config.State;
import de.eydamos.guiadvanced.helper.ButtonHelper;
import de.eydamos.guiadvanced.helper.ElementRenderHelper;
import de.eydamos.guiadvanced.misc.GuiInterface;
import de.eydamos.guiadvanced.misc.GuiPartInterface;
import de.eydamos.guiadvanced.misc.GuiStateInterface;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class Button extends GuiButton implements GuiPartInterface, GuiInterface, GuiStateInterface {
    private final int xOffset;

    private final int yOffset;

    private ElementRenderHelper renderHelper;

    public Button(int buttonId, int posX, int posY, int width, int height, String text) {
        super(buttonId, posX, posY, width, height, text);
        xOffset = posX;
        yOffset = posY;
        renderHelper = new ButtonHelper();
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
        x = guiLeft + xOffset;
        y = guiTop + yOffset;
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        boolean inX = x <= mouseX && mouseX <= x + getWidth();
        boolean inY = y <= mouseY && mouseY <= y + getHeight();

        return inX && inY;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float something) {
        if (isVisible()) {
            FontRenderer fontrenderer = mc.fontRenderer;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            hovered = isMouseOver(mouseX, mouseY);
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

            if (!isEnabled()) {
                renderHelper.setState(State.DISABLED);
            } else if (hovered) {
                renderHelper.setState(State.HOVER);
            } else {
                renderHelper.setState(State.ENABLED);
            }

            renderHelper.draw(x, y, getWidth(), getHeight());

            mouseDragged(mc, mouseX, mouseY);
            int l = 14737632;

            if (packedFGColour != 0) {
                l = packedFGColour;
            } else if (!enabled) {
                l = 10526880;
            } else if (hovered) {
                l = 16777120;
            }

            drawCenteredString(fontrenderer, displayString, x + getWidth() / 2 + 1, y + (getHeight() - 8) / 2, l);
        }
    }
}
