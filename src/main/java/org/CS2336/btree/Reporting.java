package org.CS2336.btree;

import org.CS2336.TreeSearch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by maldridge on 12/2/14.
 */
public class Reporting {
    public static void runReport(BinaryTree tree) {
        //Print tree to file
        try {
            File preorderOut = new File("preorder.out.text");
            PrintWriter printPreorder = new PrintWriter(preorderOut);
            ArrayList<String> preorderstorage = new ArrayList(tree.preorder());
            printPreorder.println(preorderstorage);
            printPreorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }
        try {
            File inorderOut = new File("inorder.out.text");
            PrintWriter printInorder = new PrintWriter(inorderOut);
            ArrayList<String> inorderstorage = new ArrayList(tree.inorder());
            printInorder.println(inorderstorage);
            printInorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }
        try {
            File postorderOut = new File("postorder.out.text");
            PrintWriter printPostorder = new PrintWriter(postorderOut);
            ArrayList<String> postorderstorage = new ArrayList(tree.postorder());
            printPostorder.println(postorderstorage);
            printPostorder.close();
        } catch (IOException e) {
            System.out.println("Exception occured " + e);
        }
    }
}
