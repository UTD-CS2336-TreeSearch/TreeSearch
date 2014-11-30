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
        System.setProperty("java.awt.headless", "true");

        GUIScreen textGUI = TerminalFacade.createGUIScreen();
        if(textGUI == null) {
            System.err.println("Couldn't allocate a terminal!");
            return;
        }
        textGUI.getScreen().startScreen();
        textGUI.setTitle("GUI Test");
        //Do GUI logic here

        MainWindow mainWindow = new MainWindow();
        textGUI.showWindow(mainWindow, GUIScreen.Position.CENTER);
        textGUI.getScreen().refresh();
        textGUI.getScreen().stopScreen();
    }


    //Create tree from tree
    //Create tree from tree
    public void buildFromFile(File newFile) throws FileNotFoundException {
        Scanner file = new Scanner(newFile);
        String input;
        if(file.hasNextLine()) {
            input = file.nextLine();
            String[] arrayedInput = input.split(" ");
            for (int i = 0; i < arrayedInput.length; i++){
                myTree.insert(arrayedInput[i]);
            }
        }
    }

    //Print tree to file
    public void printToFile() throws IOException {
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
    public MainWindow() {
        super("TreeSearch");
        addComponent(new Button("Close", new closeWindow(this)));
    }
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