package de.eydamos.guiadvanced.config;

import de.eydamos.guiadvanced.util.Rectangle;
import java.util.Hashtable;

public class FragmentHelper {
    private final int width;

    private final int height;

    private final int u;

    private final int v;

    private Hashtable<State, Offset> states;

    private State currentState;

    public FragmentHelper(int width, int height, int u, int v, Hashtable<State, Offset> states) {
        this.width = width;
        this.height = height;
        this.u = u;
        this.v = v;
        this.states = states;
        currentState = State.ENABLED;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(getWidth(), getHeight());

        Offset offset = getStateOffset();

        rectangle.setBackgroundPosition(getU() + offset.getU(), getV() + offset.getV());
        rectangle.setBackgroundSize(getWidth(), getHeight());

        return rectangle;
    }

    public void addStates(Hashtable<State, Offset> states) {
        states.forEach((state, offset) -> {
            if (!this.states.containsKey(state)) {
                this.states.put(state, offset);
            }
        });
    }

    public void setState(State state) {
        currentState = state;
    }

    private Offset getStateOffset() {
        return states.get(currentState);
    }
}
