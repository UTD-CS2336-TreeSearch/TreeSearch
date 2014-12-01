package org.CS2336;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.dialog.FileDialog;
import com.googlecode.lanterna.gui.dialog.MessageBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeSearch {
    public static BinaryTree<String> myTree = null;
    public static void main(String[] args) throws InterruptedException {
        //System.setProperty("java.awt.headless", "true");

        GUIScreen textGUI = TerminalFacade.createGUIScreen();
        if(textGUI == null) {
            System.err.println("Couldn't allocate a terminal!");
            return;
        }
        textGUI.getScreen().startScreen();
        textGUI.setTitle("GUI Test");
        //Do GUI logic here

        MainWindow mainWindow = new MainWindow(textGUI);
        textGUI.showWindow(mainWindow, GUIScreen.Position.CENTER);
        textGUI.getScreen().stopScreen();
    }


    public static void buildFromFile(File newFile) throws FileNotFoundException {
        Scanner file = new Scanner(newFile);
        String input;
        myTree = new BinaryTree<>();
        while(file.hasNextLine()) {
            input = file.nextLine();
                myTree.insert(input);
        }
    }

    //Print tree to file
    public static void printToFile() throws IOException {
        try{
            File preorderOut = new File("preorder.out.text");
            PrintWriter printPreorder = new PrintWriter(preorderOut);
            ArrayList<String> preorderstorage = new ArrayList(myTree.preorder());
            printPreorder.println(preorderstorage);
            printPreorder.close();
        } catch (IOException e){
            System.out.println("Exception occured " + e);
        }
        try {
            File inorderOut = new File("inorder.out.text");
            PrintWriter printInorder = new PrintWriter(inorderOut);
            ArrayList<String> inorderstorage = new ArrayList(myTree.inorder());
            printInorder.println(inorderstorage);
            printInorder.close();
        } catch (IOException e){
            System.out.println("Exception occured " + e);
        }
        try{
            File postorderOut = new File("postorder.out.text");
            PrintWriter printPostorder = new PrintWriter(postorderOut);
            ArrayList<String> postorderstorage = new ArrayList(myTree.postorder());
            printPostorder.println(postorderstorage);
            printPostorder.close();
        } catch (IOException e){
            System.out.println("Exception occured " + e);
        }
    }
}

class MainWindow extends Window {
    private final GUIScreen textGUI;

    public MainWindow(GUIScreen textGUI) {
        super("TreeSearch");
        this.textGUI = textGUI;
        Button closeButton = new Button("Close", new CloseWindow(this));
        addComponent(new Button("Create Tree", new CreateTree(textGUI, this, closeButton)));
        addComponent(closeButton);
    }
}

class CreateTree implements Action {
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
        if(treeFile != null) {
            try {
                TreeSearch.buildFromFile(treeFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            MessageBox.showMessageBox(textGUI, "Tree size", "Nodes: " + TreeSearch.myTree.getSize());
            if(TreeSearch.myTree != null) {
                this.mainWindow.removeComponent(closeButton);
                this.mainWindow.addComponent(new Button("Display Tree", new ShowTree(textGUI)));
                this.mainWindow.addComponent(closeButton);
            }
        }
    }
}

class ShowTree implements Action {
    GUIScreen textGUI;
    String outString="";
    private final ArrayList<flatStruct> serialTree;

    public ShowTree(GUIScreen textGUI) {
        this.textGUI = textGUI;
        serialTree = TreeSearch.myTree.flatten();
    }


    private String explode(int nodeIndex) {
        flatStruct currentNode = null;
        outString="";


        for(int i=0; i<serialTree.size(); i++) {
            if(serialTree.get(i).index == nodeIndex) {
                System.out.println("node " + nodeIndex + " is at " + i);
                currentNode = serialTree.get(i);
            }
        }

        if(currentNode == null) {
            System.out.println("Node " + nodeIndex + " was not found.");
            return "";
        }

        if(nodeIndex != 0) {
            for(int i=nodeIndex; i>0; i/=2) {
                outString += "  ";
            }
            outString += "-";
        }

        outString += currentNode.element.toString() + "\n";

        outString += explode(nodeIndex*2);
        outString += explode(nodeIndex*2+1);

        return outString;
    }

    @Override
    public void doAction() {
        MessageBox.showMessageBox(textGUI, "Tree flatness", TreeSearch.myTree.flatten().toString());

        MessageBox.showMessageBox(textGUI, "Tree", explode(1));
    }
}


class CloseWindow implements Action {
    private final Window window;

    public CloseWindow(Window window) {
        this.window = window;
    }

    @Override
    public void doAction() {
        this.window.close();
    }
}