package org.CS2336.gfx;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.dialog.FileDialog;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import org.CS2336.TreeSearch;
import org.CS2336.btree.BinaryTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CreateTree implements Action {
    private final GUIScreen textGUI;
    private final Window mainWindow;
    private final Component closeButton;
    File treeFile = null;

    public CreateTree(GUIScreen textGUI, Window mainWindow, Component closeButton) {
        this.textGUI = textGUI;
        this.mainWindow = mainWindow;
        this.closeButton = closeButton;
    }

    @Override
    public void doAction() {
        treeFile = FileDialog.showOpenFileDialog(this.textGUI, new File("."), "Select a file to become a tree:");
        if (treeFile != null) {
            try {
                Scanner file = new Scanner(treeFile);
                String input;
                TreeSearch.myTree = new BinaryTree<>();
                while (file.hasNextLine()) {
                    input = file.nextLine();
                    TreeSearch.myTree.insert(input);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            MessageBox.showMessageBox(textGUI, "Tree size", "Nodes: " + TreeSearch.myTree.getSize());
            if (TreeSearch.myTree != null) {
                this.mainWindow.removeComponent(closeButton);
                this.mainWindow.addComponent(new Button("Display Tree", new ShowTree(textGUI)));
                this.mainWindow.addComponent(new Button("Run reports", new RunReport(textGUI)));
                this.mainWindow.addComponent(closeButton);
            }
        }
    }
}
