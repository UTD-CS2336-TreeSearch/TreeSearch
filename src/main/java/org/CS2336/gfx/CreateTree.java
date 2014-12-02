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
import org.CS2336.btree.Util;

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
        try {
            Util.buildTree(treeFile);
        } catch(FileNotFoundException e) {
            MessageBox.showMessageBox(textGUI, "ERROR", "File does not exist!");
        }

        if (TreeSearch.myTree != null) {
            MessageBox.showMessageBox(textGUI, "Tree size", "Nodes: " + TreeSearch.myTree.getSize());

            this.mainWindow.removeAllComponents();
            this.mainWindow.addComponent(new Button("Create Tree" , new CreateTree(textGUI, mainWindow, closeButton)));
            this.mainWindow.addComponent(new Button("Display Tree", new ShowTree(textGUI)));
            this.mainWindow.addComponent(new Button("Run reports", new RunReport(textGUI)));
            this.mainWindow.addComponent(closeButton);
        } else {
            MessageBox.showMessageBox(textGUI, "ERROR", "Duplicate nodes are not allowed");
        }
    }
}

