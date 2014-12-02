package org.CS2336.gfx;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import org.CS2336.TreeSearch;
import org.CS2336.btree.FlatStruct;
import org.CS2336.btree.Util;

import java.util.ArrayList;


class ShowTree implements Action {
    GUIScreen textGUI;

    public ShowTree(GUIScreen textGUI) {
        this.textGUI = textGUI;
    }

    @Override
    public void doAction() {
        MessageBox.showMessageBox(textGUI, "Tree flatness", TreeSearch.myTree.flatten().toString());
        MessageBox.showMessageBox(textGUI, "Tree", Util.explode(TreeSearch.myTree));
    }
}
