package org.CS2336;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import org.CS2336.btree.BinaryTree;
import org.CS2336.btree.Util;
import org.CS2336.gfx.MainWindow;

import java.io.File;
import java.io.IOException;

public class TreeSearch {
    public static BinaryTree<String> myTree = null;

    public static void main(String[] args) throws InterruptedException, IOException {
        if(args.length == 1) {
            System.out.println(args[0]);
            Util.buildTree(new File(args[0]));
            Util.runReport(myTree);
            System.out.println(Util.explode(myTree));
            return;
        }

        if(System.console() != null) {
            //draw inline iff a console is available
            System.setProperty("java.awt.headless", "true");
        }

        GUIScreen textGUI = TerminalFacade.createGUIScreen();

        textGUI.getScreen().startScreen();
        textGUI.setTitle("GUI Test");
        //Do GUI logic here

        MainWindow mainWindow = new MainWindow(textGUI);
        textGUI.showWindow(mainWindow, GUIScreen.Position.CENTER);
        textGUI.getScreen().stopScreen();
    }
}



