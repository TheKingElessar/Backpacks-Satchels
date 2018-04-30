package de.eydamos.guiadvanced.subpart;

import de.eydamos.guiadvanced.config.State;
import de.eydamos.guiadvanced.helper.TabHelper;
import de.eydamos.guiadvanced.misc.AbstractGuiPart;
import de.eydamos.guiadvanced.misc.GuiItemRendererAwareInterface;
import de.eydamos.guiadvanced.misc.GuiPartHolderInterface;
import de.eydamos.guiadvanced.misc.GuiPartInterface;
import de.eydamos.guiadvanced.misc.WindowInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class Tab extends AbstractGuiPart implements GuiPartHolderInterface, GuiItemRendererAwareInterface {
    private static final int SIZE = 28;

    private static final int MARGIN = 1;

    private final ArrayList<GuiPartInterface> subParts = new ArrayList<>();

    private final TreeMap<String, GuiPartHolderInterface> partHolders = new TreeMap<>();

    private final String identifier;

    private final String label;

    private final Orientation orientation;

    private WindowInterface window;

    private TabHelper renderHelper;

    private GuiItemStack icon;

    public enum Orientation {
        TOP,
        BOTTOM,
    }

    public Tab(
        String identifier,
        String label,
        WindowInterface window,
        int position,
        Orientation orientation,
        ItemStack icon
    ) {
        this(identifier, label, window, position, orientation);
        this.icon = new GuiItemStack(icon, 6, 6);
    }

    public Tab(String identifier, String label, WindowInterface window, int position, Orientation orientation) {
        super(position * SIZE + position * MARGIN, orientation.equals(Orientation.TOP) ? -SIZE : window.getHeight());
        this.identifier = identifier;
        this.label = label;
        this.orientation = orientation;
        this.window = window;
        renderHelper = new TabHelper();
    }

    @Override
    public int getWidth() {
        return SIZE;
    }

    @Override
    public void setWidth(int value) {
    }

    @Override
    public int getHeight() {
        return SIZE;
    }

    @Override
    public void setHeight(int value) {
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void drawBackgroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
        if (!isVisible()) {
            return;
        }

        // drawBackgroundLayers tab
        if (isEnabled()) {
            renderHelper.setState(State.ENABLED);
        } else {
            renderHelper.setState(State.DISABLED);
        }
        renderHelper.setOrientation(orientation);
        renderHelper.draw(xPosition, yPosition, getWidth(), getHeight());

        if (icon != null) {
            icon.drawBackgroundLayers(mc, mouseX, mouseY, something);
        }

        if (isEnabled()) {
            // drawBackgroundLayers of sub parts
            subParts.forEach(subPart -> subPart.drawBackgroundLayers(mc, mouseX, mouseY, something));
        }
    }

    @Override
    public void drawForegroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
        if (!isVisible()) {
            return;
        }

        if (isEnabled()) {
            // drawForegroundLayers of sub parts
            subParts.forEach(subPart -> subPart.drawForegroundLayers(mc, mouseX, mouseY, something));
        }
    }

    @Override
    public void drawTooltips(Minecraft mc, int mouseX, int mouseY, float something) {
        if (!isVisible()) {
            return;
        }

        if (isMouseOver(mouseX, mouseY)) {
            String translated = I18n.format(label);
            window.drawHoveringText(translated, mouseX, mouseY);
        }

        if (isEnabled()) {
            // drawForegroundLayers of sub parts
            subParts.forEach(subPart -> subPart.drawTooltips(mc, mouseX, mouseY, something));
        }
    }

    @Override
    public void onMouseDown(int mouseX, int mouseY, int mouseButton) {
        if (isMouseOver(mouseX, mouseY)) {
            window.disableAllPartHolders();
            setEnabled(true);
        }
    }

    @Override
    public void onMouseUp(int mouseX, int mouseY, int state) {
        if (isEnabled()) {
            subParts.forEach(subPart -> subPart.onMouseUp(mouseX, mouseY, state));
        }
    }

    @Override
    public void onKeyDown(char typedChar, int keyCode) {
        if (isEnabled()) {
            subParts.forEach(subPart -> subPart.onKeyDown(typedChar, keyCode));
        }
    }

    @Override
    public void setAbsolutePosition(int guiLeft, int guiTop) {
        super.setAbsolutePosition(guiLeft, guiTop);

        if (xPosition == guiLeft) {
            renderHelper.setFirst(true);
        } else if (xPosition + getWidth() == guiLeft + window.getWidth()) {
            renderHelper.setLast(true);
        }

        if (icon != null) {
            icon.setAbsolutePosition(xPosition, yPosition);
        }

        subParts.forEach(subPart -> subPart.setAbsolutePosition(guiLeft, guiTop));
    }

    @Override
    public void addSubPart(GuiPartInterface subPart) {
        subParts.add(subPart);
    }

    @Override
    public void addSubPart(GuiPartInterface subPart, String parent) {
        if (!partHolders.containsKey(parent)) {
            throw new IllegalArgumentException("Parent with identifier" + parent + " does not exists.");
        }

        partHolders.get(parent).addSubPart(subPart);

        if (subPart instanceof GuiPartHolderInterface) {
            partHolders.put(((GuiPartHolderInterface) subPart).getIdentifier(), (GuiPartHolderInterface) subPart);
        }
    }

    @Override
    public void removeSubPart(GuiPartInterface subPart) {
        subParts.remove(subPart);

        partHolders.values().forEach(holder -> holder.removeSubPart(subPart));
    }

    @Override
    public void clearSubParts() {
        subParts.clear();
    }

    @Override
    public List<GuiPartInterface> getSubParts() {
        return subParts;
    }

    @Override
    public void setGuiItemRenderer(RenderItem guiItemRenderer) {
        subParts.forEach(subPart -> {
            if (subPart instanceof GuiItemRendererAwareInterface) {
                ((GuiItemRendererAwareInterface) subPart).setGuiItemRenderer(guiItemRenderer);
            }
        });

        if (icon != null) {
            icon.setGuiItemRenderer(guiItemRenderer);
        }
    }

    @Override
    public void setEnabled(boolean value) {
        super.setEnabled(value);

        subParts.forEach(subPart -> subPart.setEnabled(value));
    }
}
