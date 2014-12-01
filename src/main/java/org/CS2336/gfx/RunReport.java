package org.CS2336.gfx;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import org.CS2336.TreeSearch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by maldridge on 12/1/14.
 */
class RunReport implements Action {
    private final GUIScreen textGUI;
    public RunReport(GUIScreen textGUI) {
        this.textGUI = textGUI;
    }


    @Override
    public void doAction() {
        //Print tree to file
        try {
            File preorderOut = new File("preorder.out.text");
            PrintWriter printPreorder = new PrintWriter(preorderOut);
            ArrayList<String> preorderstorage = new ArrayList(TreeSearch.myTree.preorder());
            printPreorder.println(preorderstorage);
            printPreorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }
        try {
            File inorderOut = new File("inorder.out.text");
            PrintWriter printInorder = new PrintWriter(inorderOut);
            ArrayList<String> inorderstorage = new ArrayList(TreeSearch.myTree.inorder());
            printInorder.println(inorderstorage);
            printInorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }
        try {
            File postorderOut = new File("postorder.out.text");
            PrintWriter printPostorder = new PrintWriter(postorderOut);
            ArrayList<String> postorderstorage = new ArrayList(TreeSearch.myTree.postorder());
            printPostorder.println(postorderstorage);
            printPostorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }
        MessageBox.showMessageBox(textGUI, "Success!", "Report generation successful!");
    }
}
