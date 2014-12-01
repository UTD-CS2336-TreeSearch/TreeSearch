package org.CS2336.gfx;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import org.CS2336.TreeSearch;
import org.CS2336.btree.FlatStruct;

import java.util.ArrayList;


class ShowTree implements Action {
    private final ArrayList<FlatStruct> serialTree;
    GUIScreen textGUI;
    String outString = "";

    public ShowTree(GUIScreen textGUI) {
        this.textGUI = textGUI;
        serialTree = TreeSearch.myTree.flatten();
    }


    private String explode(int nodeIndex) {
        FlatStruct currentNode = null;
        outString = "";


        for (int i = 0; i < serialTree.size(); i++) {
            if (serialTree.get(i).getIndex() == nodeIndex) {
                currentNode = serialTree.get(i);
            }
        }

        if (currentNode == null) {
            return "";
        }

        if (nodeIndex != 0) {
            for (int i = nodeIndex; i > 0; i /= 2) {
                outString += "  ";
            }
            outString += "-";
        }

        outString += currentNode.getElement().toString() + "\n";

        outString += explode(nodeIndex * 2);
        outString += explode(nodeIndex * 2 + 1);

        return outString;
    }

    @Override
    public void doAction() {
        MessageBox.showMessageBox(textGUI, "Tree flatness", TreeSearch.myTree.flatten().toString());
        MessageBox.showMessageBox(textGUI, "Tree", explode(1));
    }
}
