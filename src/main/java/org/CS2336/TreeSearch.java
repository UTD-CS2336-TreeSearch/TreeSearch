package org.CS2336;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

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
        Terminal term = textGUI.getScreen().getTerminal();
        //Do GUI logic here

        MyWindow myWindow = new MyWindow();
        textGUI.showWindow(myWindow, GUIScreen.Position.CENTER);
        textGUI.getActiveWindow().close();
        textGUI.getScreen().stopScreen();
        textGUI.invalidate();
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
    public MyWindow() {
        super("My Window!");
        Panel horisontalPanel = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
        horisontalPanel.addComponent(new Label("Welcome to TreeSearch"));
        addComponent(horisontalPanel);
    }
}