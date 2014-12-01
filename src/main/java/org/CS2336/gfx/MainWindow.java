package org.CS2336.gfx;

import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import org.CS2336.gfx.CreateTree;

/**
 * Created by maldridge on 12/1/14.
 */
public class MainWindow extends Window {
    private final GUIScreen textGUI;

    public MainWindow(GUIScreen textGUI) {
        super("TreeSearch");
        this.textGUI = textGUI;
        Button closeButton = new Button("Close", new CloseWindow(this));
        addComponent(new Button("Create Tree", new CreateTree(textGUI, this, closeButton)));
        addComponent(closeButton);
    }
}
