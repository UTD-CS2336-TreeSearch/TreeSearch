package org.CS2336.gfx;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import org.CS2336.TreeSearch;
import org.CS2336.btree.Util;

import java.io.IOException;


class RunReport implements Action {
    private final GUIScreen textGUI;

    public RunReport(GUIScreen textGUI) {
        this.textGUI = textGUI;
    }


    @Override
    public void doAction() {
        try {
            Util.runReport(TreeSearch.myTree);
            MessageBox.showMessageBox(textGUI, "Success!", "Report generation successful!");
        } catch(IOException e) {
            MessageBox.showMessageBox(textGUI, "ERROR", "A serious error has occurred" + e.toString());
        }
    }
}
