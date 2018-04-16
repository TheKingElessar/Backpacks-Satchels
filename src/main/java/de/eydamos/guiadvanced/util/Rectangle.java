package de.eydamos.guiadvanced.util;

import de.eydamos.backpack.misc.Constants;
import de.eydamos.guiadvanced.util.RenderHelper.BackgroundRepeat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public class Rectangle {
    protected int width;
    protected int height;
    protected int u = 0;
    protected int v = 0;
    protected int z = 0;
    protected int uMax = 1;
    protected int vMax = 1;
    protected ResourceLocation graphic = Constants.guiCombined;
    protected BackgroundRepeat repeat = BackgroundRepeat.NONE;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected Minecraft getMinecraft() {
        return FMLClientHandler.instance().getClient();
    }

    public void setWidth(int value) {
        width = value;
    }

    public void setHeight(int value) {
        height = value;
    }

    public void setBackgroundPosition(int xOffset, int yOffset) {
        u = xOffset;
        v = yOffset;
    }

    public void setBackgroundSize(int sizeX, int sizeY) {
        uMax = sizeX;
        vMax = sizeY;
    }

    public void setBackgroundRepeat(BackgroundRepeat backgroundRepeat) {
        repeat = backgroundRepeat;
    }

    public void setBackground(ResourceLocation resourceLocation) {
        graphic = resourceLocation;
    }

    // look at class net.minecraft.client.gui.Gui if somethings breaks
    public void draw(int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        getMinecraft().getTextureManager().bindTexture(graphic);

        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        if(repeat == BackgroundRepeat.NONE) {
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            addVertexWithUV(bufferbuilder, x, y + height, z, u * f, (v + height) * f1);
            addVertexWithUV(bufferbuilder, x + width, y + height, z, (u + width) * f, (v + height) * f1);
            addVertexWithUV(bufferbuilder, x + width, y, z, (u + width) * f, v * f1);
            addVertexWithUV(bufferbuilder, x, y, z, u * f, v * f1);
            tessellator.draw();
        } else if(repeat == BackgroundRepeat.REPEAT) {
            int drawHeight = vMax = Math.min(height, vMax);
            int drawWidth = uMax = Math.min(width, uMax);
            for(int i = 0; i <= width; i += uMax) {
                for(int j = 0; j <= height; j += vMax) {
                    drawWidth = i + uMax > width ? width : i + uMax;
                    drawHeight = j + vMax > height ? height : j + vMax;
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    addVertexWithUV(bufferbuilder, x + i, y + drawHeight, z, u * f, (v + vMax) * f1);
                    addVertexWithUV(bufferbuilder, x + drawWidth, y + drawHeight, z, (u + uMax) * f, (v + vMax) * f1);
                    addVertexWithUV(bufferbuilder, x + drawWidth, y, z, (u + uMax) * f, v * f1);
                    addVertexWithUV(bufferbuilder, x + i, y, z, u * f, v * f1);
                    tessellator.draw();
                }
            }
        } else if(repeat == BackgroundRepeat.REPEAT_X) {
            int drawHeight = vMax = Math.min(height, vMax);
            int drawWidth;
            for(int i = 0; i <= width; i += uMax) {
                drawWidth = i + uMax > width ? width : i + uMax;
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                addVertexWithUV(bufferbuilder, x + i, y + drawHeight, z, u * f, (v + drawHeight) * f1);
                addVertexWithUV(bufferbuilder, x + drawWidth, y + drawHeight, z, (u + uMax) * f, (v + drawHeight) * f1);
                addVertexWithUV(bufferbuilder, x + drawWidth, y, z, (u + uMax) * f, v * f1);
                addVertexWithUV(bufferbuilder, x + i, y, z, u * f, v * f1);
                tessellator.draw();
            }
        } else if(repeat == BackgroundRepeat.REPEAT_Y) {
            int drawWidth = uMax = Math.min(width, uMax);
            int drawHeight;
            for(int i = 0; i <= height; i += vMax) {
                drawHeight = i + vMax > height ? height : i + vMax;
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                addVertexWithUV(bufferbuilder, x, y + drawHeight, z, u * f, (v + vMax) * f1);
                addVertexWithUV(bufferbuilder, x + drawWidth, y + drawHeight, z, (u + drawWidth) * f, (v + vMax) * f1);
                addVertexWithUV(bufferbuilder, x + drawWidth, y + i, z, (u + drawWidth) * f, v * f1);
                addVertexWithUV(bufferbuilder, x, y + i, z, u * f, v * f1);
                tessellator.draw();
            }
        } else {
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            addVertexWithUV(bufferbuilder, x, y + height, z, u * f, vMax * f1);
            addVertexWithUV(bufferbuilder, x + width, y + height, z, uMax * f, vMax * f1);
            addVertexWithUV(bufferbuilder, x + width, y, z, uMax * f, v * f1);
            addVertexWithUV(bufferbuilder, x, y, z, u * f, v * f1);
            tessellator.draw();
        }
    }

    private void addVertexWithUV(BufferBuilder worldRenderer, int x, int y, int z, float u, float v) {
        worldRenderer.pos(x, y, z).tex(u, v).endVertex();
    }
}
