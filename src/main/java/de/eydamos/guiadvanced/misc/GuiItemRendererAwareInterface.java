package de.eydamos.guiadvanced.misc;

import net.minecraft.client.renderer.RenderItem;

public interface GuiItemRendererAwareInterface {
    /**
     * Sets the item renderer on the class.
     *
     * @param guiItemRenderer The item renderer.
     */
    void setGuiItemRenderer(RenderItem guiItemRenderer);
}
