package de.eydamos.guiadvanced.subpart;

import de.eydamos.guiadvanced.helper.ElementRenderHelper;
import de.eydamos.guiadvanced.helper.SlotHelper;
import de.eydamos.guiadvanced.inventory.SlotWithState;
import de.eydamos.guiadvanced.misc.AbstractGuiPart;
import net.minecraft.client.Minecraft;

public class GuiSlot extends AbstractGuiPart {
    private final SlotWithState slot;

    private ElementRenderHelper renderHelper;

    public GuiSlot(SlotWithState slot) {
        this(slot, 18, 18);
    }

    public GuiSlot(SlotWithState slot, int widthHeight) {
        this(slot, widthHeight, widthHeight);
    }

    public GuiSlot(SlotWithState slot, int width, int height) {
        super(slot.xPos - 1, slot.yPos - 1);
        this.slot = slot;
        setWidth(width);
        setHeight(height);
        renderHelper = new SlotHelper();
    }

    @Override
    public void drawBackgroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
        if (!isVisible()) {
            return;
        }

        renderHelper.draw(xPosition, yPosition, getWidth(), getHeight());
    }

    @Override
    public boolean isEnabled() {
        return slot.isEnabled();
    }

    @Override
    public void setEnabled(boolean value) {
        slot.setEnabled(value);
    }
}
