package de.eydamos.guiadvanced.helper;

import de.eydamos.guiadvanced.config.Element;
import de.eydamos.guiadvanced.config.State;
import de.eydamos.guiadvanced.subpart.Tab.Orientation;

public class TabHelper extends ElementRenderHelper {
    private Orientation orientation;

    private boolean isFirst = false;

    private boolean isLast = false;

    public TabHelper() {
        super(Element.WINDOW);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    @Override
    public void draw(int posX, int posY, int width, int height) {
        int border = element.getBorderWidth();

        // TODO the 1 in the rendering of the inner corners should be calculated
        // it is the difference between the size of an outer corner and an inner corner

        switch (orientation) {
            case TOP:
                drawOuterCornerTopLeft(posX, posY);
                drawOuterBorderTop(posX + border, posY, width - 2 * border);
                drawOuterCornerTopRight(posX + width - border, posY);
                drawOuterBorderRight(posX + width - border, posY + border, height - border);
                drawOuterBorderLeft(posX, posY + border, height - border);
                if (element.getState().equals(State.ENABLED)) {
                    if (isFirst) {
                        drawOuterBorderLeft(posX, posY + height, border);
                        drawInnerCornerBottomLeft(posX + width - border, posY + height - 1);
                    } else if (isLast) {
                        drawOuterBorderRight(posX + width - border, posY + height, border);
                        drawInnerCornerBottomRight(posX, posY + height - 1);
                    } else {
                        drawInnerCornerBottomLeft(posX + width - border, posY + height - 1);
                        drawInnerCornerBottomRight(posX - 1, posY + height - 1);
                    }
                    drawBackground(posX + border, posY + border, width - 2 * border, height);
                } else {
                    drawBackground(posX + border, posY + border, width - 2 * border, height - border);
                }
                break;
            case BOTTOM:
                drawOuterBorderRight(posX + width - border, posY, height - border);
                drawOuterCornerBottomRight(posX + width - border, posY + height - border);
                drawOuterBorderBottom(posX + border, posY + height - border, width - 2 * border);
                drawOuterCornerBottomLeft(posX, posY + height - border);
                drawOuterBorderLeft(posX, posY, height - border);
                if (element.getState().equals(State.ENABLED)) {
                    if (isFirst) {
                        drawOuterBorderLeft(posX, posY - border, border);
                        drawInnerCornerTopLeft(posX + width - border, posY - border + 1);
                    } else if (isLast) {
                        drawOuterBorderRight(posX + width - border, posY - border, border);
                        drawInnerCornerTopRight(posX, posY - border + 1);
                    } else {
                        drawInnerCornerTopRight(posX - 1, posY - border);
                        drawInnerCornerTopLeft(posX + width - border, posY - border + 1);
                    }
                    drawBackground(posX + border, posY - border, width - 2 * border, height);
                } else {
                    drawBackground(posX + border, posY, width - 2 * border, height - border);
                }
                break;
        }
    }
}
