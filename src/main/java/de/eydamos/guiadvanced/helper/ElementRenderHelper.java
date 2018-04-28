package de.eydamos.guiadvanced.helper;

import de.eydamos.guiadvanced.config.BackgroundRepeat;
import de.eydamos.guiadvanced.config.Element;
import de.eydamos.guiadvanced.config.Fragment;
import de.eydamos.guiadvanced.config.FragmentHelper;
import de.eydamos.guiadvanced.config.State;

public class ElementRenderHelper {
    Element element;

    public ElementRenderHelper(Element element) {
        this.element = element;
    }

    public void setState(State state) {
        element.setState(state);
    }

    public void drawOuterCornerTopLeft(int posX, int posY) {
        FragmentHelper fragment = element.getFragment(Fragment.OUTER_CORNER__TOP_LEFT);
        fragment.getRectangle()
            .draw(posX, posY);
    }

    public void drawOuterCornerTopRight(int posX, int posY) {
        FragmentHelper fragment = element.getFragment(Fragment.OUTER_CORNER__TOP_RIGHT);
        fragment.getRectangle()
            .draw(posX, posY);
    }

    public void drawOuterCornerBottomLeft(int posX, int posY) {
        FragmentHelper fragment = element.getFragment(Fragment.OUTER_CORNER__BOTTOM_LEFT);
        fragment.getRectangle()
            .draw(posX, posY);
    }

    public void drawOuterCornerBottomRight(int posX, int posY) {
        FragmentHelper fragment = element.getFragment(Fragment.OUTER_CORNER__BOTTOM_RIGHT);
        fragment.getRectangle()
            .draw(posX, posY);
    }

    public void drawInnerCornerTopLeft(int posX, int posY) {
        FragmentHelper fragment = element.getFragment(Fragment.INNER_CORNER__TOP_LEFT);
        fragment.getRectangle()
            .draw(posX, posY);
    }

    public void drawInnerCornerTopRight(int posX, int posY) {
        FragmentHelper fragment = element.getFragment(Fragment.INNER_CORNER__TOP_RIGHT);
        fragment.getRectangle()
            .draw(posX, posY);
    }

    public void drawInnerCornerBottomLeft(int posX, int posY) {
        FragmentHelper fragment = element.getFragment(Fragment.INNER_CORNER__BOTTOM_LEFT);
        fragment.getRectangle()
            .draw(posX, posY);
    }

    public void drawInnerCornerBottomRight(int posX, int posY) {
        FragmentHelper fragment = element.getFragment(Fragment.INNER_CORNER__BOTTOM_RIGHT);
        fragment.getRectangle()
            .draw(posX, posY);
    }

    public void drawOuterBorderTop(int posX, int posY, int width) {
        FragmentHelper fragment = element.getFragment(Fragment.OUTER_BORDER__TOP);
        fragment.getRectangle()
            .setWidth(width)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT_X)
            .draw(posX, posY);
    }

    public void drawOuterBorderRight(int posX, int posY, int height) {
        FragmentHelper fragment = element.getFragment(Fragment.OUTER_BORDER__RIGHT);
        fragment.getRectangle()
            .setHeight(height)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT_Y)
            .draw(posX, posY);
    }

    public void drawOuterBorderBottom(int posX, int posY, int width) {
        FragmentHelper fragment = element.getFragment(Fragment.OUTER_BORDER__BOTTOM);
        fragment.getRectangle()
            .setWidth(width)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT_X)
            .draw(posX, posY);
    }

    public void drawOuterBorderLeft(int posX, int posY, int height) {
        FragmentHelper fragment = element.getFragment(Fragment.OUTER_BORDER__LEFT);
        fragment.getRectangle()
            .setHeight(height)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT_Y)
            .draw(posX, posY);
    }

    public void drawInnerBorderTop(int posX, int posY, int width) {
        FragmentHelper fragment = element.getFragment(Fragment.INNER_BORDER__TOP);
        fragment.getRectangle()
            .setWidth(width)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT_X)
            .draw(posX, posY);
    }

    public void drawInnerBorderRight(int posX, int posY, int height) {
        FragmentHelper fragment = element.getFragment(Fragment.INNER_BORDER__RIGHT);
        fragment.getRectangle()
            .setHeight(height)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT_Y)
            .draw(posX, posY);
    }

    public void drawInnerBorderBottom(int posX, int posY, int width) {
        FragmentHelper fragment = element.getFragment(Fragment.INNER_BORDER__BOTTOM);
        fragment.getRectangle()
            .setWidth(width)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT_X)
            .draw(posX, posY);
    }

    public void drawInnerBorderLeft(int posX, int posY, int height) {
        FragmentHelper fragment = element.getFragment(Fragment.INNER_BORDER__LEFT);
        fragment.getRectangle()
            .setHeight(height)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT_Y)
            .draw(posX, posY);
    }

    public void drawBackground(int posX, int posY, int width, int height) {
        FragmentHelper fragment = element.getFragment(Fragment.BACKGROUND);
        fragment.getRectangle()
            .setWidth(width)
            .setHeight(height)
            .setBackgroundRepeat(BackgroundRepeat.REPEAT)
            .draw(posX, posY);
    }

    public void draw(int posX, int posY, int width, int height) {
        int border = element.getBorderWidth();

        drawOuterCornerTopLeft(posX, posY);
        drawOuterBorderTop(posX + border, posY, width - border * 2);
        drawOuterCornerTopRight(posX + width - border, posY);
        drawOuterBorderRight(posX + width - border, posY + border, height - border * 2);
        drawOuterCornerBottomRight(posX + width - border, posY + height - border);
        drawOuterBorderBottom(posX + border, posY + height - border, width - border * 2);
        drawOuterCornerBottomLeft(posX, posY + height - border);
        drawOuterBorderLeft(posX, posY + border, height - border * 2);
        drawBackground(posX + border, posY + border, width - border * 2, height - border * 2);
    }
}
