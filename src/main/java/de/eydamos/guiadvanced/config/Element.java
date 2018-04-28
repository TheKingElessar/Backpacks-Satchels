package de.eydamos.guiadvanced.config;

import java.util.Hashtable;

public enum Element {
    BUTTON,
    BUTTON2,
    SLIDER,
    SLOT,
    TEXT_FIELD,
    TEXT_FIELD2,
    WINDOW;

    private Hashtable<Fragment, FragmentHelper> fragments = new Hashtable<>();

    private int borderWidth = 0;

    private State state;

    public void addFragment(Fragment fragment, FragmentHelper fragmentHelper) {
        // width and height of a corner are the same so we use it to determine the width of the border
        if (borderWidth == 0 && fragment.isCorner()) {
            borderWidth = fragmentHelper.getWidth();
        }
        fragments.put(fragment, fragmentHelper);
    }

    public FragmentHelper getFragment(Fragment fragment) {
        return fragments.get(fragment);
    }

    public void addStates(Hashtable<State, Offset> states) {
        fragments.forEach((fragment, fragmentHelper) -> fragmentHelper.addStates(states));
    }

    public void setState(State state) {
        this.state = state;
        fragments.forEach((fragment, fragmentHelper) -> fragmentHelper.setState(state));
    }

    public State getState() {
        return state;
    }

    public String getFileName() {
        return name().toLowerCase();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public static void clear() {
        for (Element element : values()) {
            element.fragments.clear();
            element.borderWidth = 0;
        }
    }
}
