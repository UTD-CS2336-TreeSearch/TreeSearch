package org.CS2336;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.dialog.MessageBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeSearch {
    BinaryTree<String> myTree = new BinaryTree<String>();
    public static void main(String[] args) throws InterruptedException {
        GUIScreen textGUI = TerminalFacade.createGUIScreen();
        if(textGUI == null) {
            System.err.println("Couldn't allocate a terminal!");
            return;
        }
        textGUI.getScreen().startScreen();
        textGUI.setTitle("GUI Test");
        //Do GUI logic here

        System.out.println("made it to window launch");
        MyWindow myWindow = new MyWindow();
        System.out.println("instantiated; drawing");
        textGUI.showWindow(myWindow, GUIScreen.Position.CENTER);
        System.out.println("drew window");
        textGUI.getScreen().refresh();
        textGUI.getScreen().refresh();
        textGUI.getScreen().stopScreen();
    }


    //Create tree from tree
    public void buildFromFile(File newFile) throws FileNotFoundException {
        Scanner file = new Scanner(newFile);
        String input;
        input = file.nextLine();
        String[] arrayedInput = input.split(" ");
        for (int i = 0; i < arrayedInput.length; i++) {
            myTree.insert(arrayedInput[i]);
        }
    }

    //Print tree to file
    public void printToFile() throws IOException {
        try {
            File preorderOut = new File("preorder.out.text");
            PrintWriter printPreorder = new PrintWriter(preorderOut);
            ArrayList<String> preorderstorage = new ArrayList(myTree.preorder());
            printPreorder.println(preorderstorage);
            printPreorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }
        try {
            File inorderOut = new File("inorder.out.text");
            PrintWriter printInorder = new PrintWriter(inorderOut);
            ArrayList<String> inorderstorage = new ArrayList(myTree.inorder());
            printInorder.println(inorderstorage);
            printInorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }
        try {
            File postorderOut = new File("postorder.out.text");
            PrintWriter printPostorder = new PrintWriter(postorderOut);
            ArrayList<String> postorderstorage = new ArrayList(myTree.postorder());
            printPostorder.println(postorderstorage);
            printPostorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }

    }
}

class MyWindow extends Window {
    public MyWindow()
    {
        super("My Window!");
        addComponent(new Button("Button with no action"));
        addComponent(new Button("Button with action", new Action() {
            @Override
            public void doAction() {
                MessageBox.showMessageBox(getOwner(), "Hello", "You selected the button with an action attached to it!");
            }
        }));
        addComponent(new Button("Close", new closeWindow(this)));
    }

class closeWindow implements Action {
    private final Window window;

    public closeWindow(Window window) {
        this.window = window;
    }

    @Override
    public void doAction() {
        this.window.close();
    }
}
}