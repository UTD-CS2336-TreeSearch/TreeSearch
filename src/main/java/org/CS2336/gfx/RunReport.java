package org.CS2336.gfx;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import org.CS2336.TreeSearch;
import org.CS2336.btree.Reporting;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class RunReport implements Action {
    private final GUIScreen textGUI;

    public RunReport(GUIScreen textGUI) {
        this.textGUI = textGUI;
    }


    @Override
    public void doAction() {
        Reporting.runReport(TreeSearch.myTree);
        MessageBox.showMessageBox(textGUI, "Success!", "Report generation successful!");
    }
}
