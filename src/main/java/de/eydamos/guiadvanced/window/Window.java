package de.eydamos.guiadvanced.window;

import de.eydamos.guiadvanced.config.State;
import de.eydamos.guiadvanced.helper.ElementRenderHelper;
import de.eydamos.guiadvanced.helper.WindowHelper;
import de.eydamos.guiadvanced.misc.GuiItemRendererAwareInterface;
import de.eydamos.guiadvanced.misc.GuiPartHolderInterface;
import de.eydamos.guiadvanced.misc.GuiPartInterface;
import de.eydamos.guiadvanced.misc.WindowInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class Window extends GuiScreen implements WindowInterface {
    private List<GuiPartInterface> subParts = new ArrayList<>();

    private TreeMap<String, GuiPartHolderInterface> partHolders = new TreeMap<>();

    private String identifier;

    private ElementRenderHelper renderHelper;

    private int xSize = 0;

    private int ySize = 0;

    private int guiLeft = 0;

    private int guiTop = 0;

    public Window(String identifier) {
        this.identifier = identifier;
        renderHelper = new WindowHelper();
    }

    @Override
    public int getWidth() {
        return xSize;
    }

    @Override
    public void setWidth(int value) {
        xSize = value;
    }

    @Override
    public int getHeight() {
        return ySize;
    }

    @Override
    public void setHeight(int value) {
        ySize = value;
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        boolean inX = guiLeft <= mouseX && mouseX <= guiLeft + getWidth();
        boolean inY = guiTop <= mouseY && mouseY <= guiTop + getHeight();

        return inX && inY;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addSubPart(GuiPartInterface subPart) {
        subParts.add(subPart);
    }

    public void addSubPart(GuiPartInterface subPart, String parent) {
        if (!partHolders.containsKey(parent)) {
            throw new IllegalArgumentException("Parent with identifier" + parent + " does not exists.");
        }

        partHolders.get(parent).addSubPart(subPart);

        if (subPart instanceof GuiPartHolderInterface) {
            partHolders.put(((GuiPartHolderInterface) subPart).getIdentifier(), (GuiPartHolderInterface) subPart);
        }
    }

    public void removeSubPart(GuiPartInterface subPart) {
        subParts.remove(subPart);

        partHolders.values().forEach(holder -> holder.removeSubPart(subPart));
    }

    public void clearSubParts() {
        subParts.clear();
    }

    public List<GuiPartInterface> getSubParts() {
        return subParts;
    }

    @Override
    public void disableAllPartHolders() {
        partHolders.values().forEach(holder -> holder.setEnabled(false));
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        subParts.forEach(subPart -> subPart.onMouseDown(mouseX, mouseY, mouseButton));
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        subParts.forEach(subPart -> subPart.onMouseUp(mouseX, mouseY, state));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        subParts.forEach(subPart -> subPart.onKeyDown(typedChar, keyCode));
    }

    /* ========== overrides from GuiScreen ========== */
    @Override
    public void initGui() {
        guiLeft = (width - xSize) / 2;
        guiTop = (height - ySize) / 2;

        for (GuiPartInterface guiPart : subParts) {
            guiPart.setAbsolutePosition(guiLeft, guiTop);
            if (guiPart instanceof GuiButton) {
                buttonList.add((GuiButton) guiPart);
            }
        }

        partHolders.values().forEach(holder -> {
            if (holder instanceof GuiItemRendererAwareInterface) {
                ((GuiItemRendererAwareInterface) holder).setGuiItemRenderer(itemRender);
            }
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        // draw background
        renderHelper.setState(State.ENABLED);
        renderHelper.draw(guiLeft, guiTop, getWidth(), getHeight());

        // drawBackgroundLayers subparts
        subParts.forEach(subPart -> subPart.drawBackgroundLayers(mc, mouseX, mouseY, partialTicks));

        super.drawScreen(mouseX, mouseY, partialTicks);

        // drawForegroundLayers of sub parts
        subParts.forEach(subPart -> subPart.drawForegroundLayers(mc, mouseX, mouseY, partialTicks));

        // draw tooltips of sub parts
        subParts.forEach(subPart -> subPart.drawTooltips(mc, mouseX, mouseY, partialTicks));
    }
}
