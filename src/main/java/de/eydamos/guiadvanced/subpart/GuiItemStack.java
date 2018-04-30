package de.eydamos.guiadvanced.subpart;

import de.eydamos.guiadvanced.misc.AbstractGuiPart;
import de.eydamos.guiadvanced.misc.GuiItemRendererAwareInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class GuiItemStack extends AbstractGuiPart implements GuiItemRendererAwareInterface {
    private ItemStack icon;

    private RenderItem guiItemRenderer;

    public GuiItemStack(ItemStack icon, int posX, int posY) {
        super(posX, posY);
        this.icon = icon;
    }

    @Override
    public void drawBackgroundLayers(Minecraft mc, int mouseX, int mouseY, float something) {
        if (!isVisible()) {
            return;
        }

        RenderHelper.enableGUIStandardItemLighting();
        guiItemRenderer.renderItemAndEffectIntoGUI(icon, xPosition, yPosition);
        RenderHelper.disableStandardItemLighting();
    }

    @Override
    public void setGuiItemRenderer(RenderItem guiItemRenderer) {
        this.guiItemRenderer = guiItemRenderer;
    }
}
