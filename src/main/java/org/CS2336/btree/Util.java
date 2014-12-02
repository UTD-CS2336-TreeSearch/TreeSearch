package org.CS2336.btree;

import org.CS2336.TreeSearch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by maldridge on 12/2/14.
 */
public class Util {
    public static void runReport(BinaryTree tree) throws IOException {

        //attempt to create a directory for the reports to go in
        File reportDir = new File("report");
        if(!reportDir.exists()) {
            reportDir.mkdir();
        }

        File treeDir = new File(reportDir.getCanonicalPath(), tree.getName());
        if(!treeDir.exists()) {
            treeDir.mkdir();
        }


        //Print tree to file
            File preorderOut = new File(treeDir.getCanonicalPath(), "preorder.txt");
            PrintWriter printPreorder = new PrintWriter(preorderOut);
            ArrayList<String> preorderstorage = new ArrayList(tree.preorder());
            printPreorder.println(preorderstorage);
            printPreorder.close();


            File inorderOut = new File(treeDir.getCanonicalPath(), "inorder.txt");
            PrintWriter printInorder = new PrintWriter(inorderOut);
            ArrayList<String> inorderstorage = new ArrayList(tree.inorder());
            printInorder.println(inorderstorage);
            printInorder.close();

            File postorderOut = new File(treeDir.getCanonicalPath(), "postorder.txt");
            PrintWriter printPostorder = new PrintWriter(postorderOut);
            ArrayList<String> postorderstorage = new ArrayList(tree.postorder());
            printPostorder.println(postorderstorage);
            printPostorder.close();
    }
}
