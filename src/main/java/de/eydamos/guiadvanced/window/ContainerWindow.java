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
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ContainerWindow extends GuiContainer implements WindowInterface {
    private ArrayList<GuiPartInterface> subParts = new ArrayList<>();

    private TreeMap<String, GuiPartHolderInterface> partHolders = new TreeMap<>();

    private ElementRenderHelper renderHelper;

    public ContainerWindow(Container container) {
        super(container);
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

    public void addSubPart(GuiPartInterface subPart) {
        subParts.add(subPart);

        if (subPart instanceof GuiPartHolderInterface) {
            partHolders.put(((GuiPartHolderInterface) subPart).getIdentifier(), (GuiPartHolderInterface) subPart);
        }
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

    /* ========== overrides from GuiContainer ========== */
    @Override
    public void initGui() {
        super.initGui();

        subParts.forEach(subPart -> {
            subPart.setAbsolutePosition(guiLeft, guiTop);
            if (subPart instanceof GuiButton) {
                buttonList.add((GuiButton) subPart);
            }
        });

        partHolders.values().forEach(holder -> {
            if (holder instanceof GuiItemRendererAwareInterface) {
                ((GuiItemRendererAwareInterface) holder).setGuiItemRenderer(itemRender);
            }
        });
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float something, int mouseX, int mouseY) {
        // draw background
        renderHelper.setState(State.ENABLED);
        renderHelper.draw(guiLeft, guiTop, getWidth(), getHeight());

        // drawBackgroundLayers of sub parts
        subParts.forEach(subPart -> subPart.drawBackgroundLayers(mc, mouseX, mouseY, something));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        // draw slot content, hovering slots etc.
        super.drawScreen(mouseX, mouseY, partialTicks);

        // drawForegroundLayers of sub parts
        subParts.forEach(subPart -> subPart.drawForegroundLayers(mc, mouseX, mouseY, partialTicks));

        // draw tooltips of sub parts
        subParts.forEach(subPart -> subPart.drawTooltips(mc, mouseX, mouseY, partialTicks));
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected boolean hasClickedOutside(int mouseX, int mouseY, int guiLeft, int guiTop) {
        if (super.hasClickedOutside(mouseX, mouseY, guiLeft, guiTop)) {
            return partHolders.values().stream().noneMatch(holder -> holder.isMouseOver(mouseX, mouseY));
        }

        return false;
    }
}
