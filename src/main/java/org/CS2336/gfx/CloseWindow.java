package org.CS2336.gfx;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;

/**
 * Created by maldridge on 12/1/14.
 */
public class CloseWindow implements Action {
    private final Window window;

    public CloseWindow(Window window) {
        this.window = window;
    }

    @Override
    public void doAction() {
        this.window.close();
    }
}
