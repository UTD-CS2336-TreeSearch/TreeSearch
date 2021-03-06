package org.CS2336.btree;

import com.googlecode.lanterna.gui.dialog.MessageBox;
import org.CS2336.TreeSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by maldridge on 12/2/14.
 */
public class Util {

    public static String runAllReports(BinaryTree tree) throws IOException {
        String fullReport = "";
        fullReport += "In-Order " + reportInOrder(tree) + '\n';
        fullReport += "Post-Order " + reportPostOrder(tree) +'\n';
        fullReport += "Pre-Order " + reportPreOrder(tree) + '\n';
        return fullReport;
    }

    public static String reportPreOrder(BinaryTree tree) throws IOException {

        //attempt to create a directory for the reports to go in
        File reportDir = new File("report");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }

        File treeDir = new File(reportDir.getCanonicalPath(), tree.getName());
        if (!treeDir.exists()) {
            treeDir.mkdir();
        }

        File preorderOut = new File(treeDir.getCanonicalPath(), "preorder.txt");
        PrintWriter printPreorder = new PrintWriter(preorderOut);
        ArrayList<String> preorderstorage = new ArrayList(tree.preorder());
        printPreorder.println(preorderstorage);
        printPreorder.close();
        return preorderstorage.toString();
    }

    public static String reportInOrder(BinaryTree tree) throws IOException {

        //attempt to create a directory for the reports to go in
        File reportDir = new File("report");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }

        File treeDir = new File(reportDir.getCanonicalPath(), tree.getName());
        if (!treeDir.exists()) {
            treeDir.mkdir();
        }

        File inorderOut = new File(treeDir.getCanonicalPath(), "inorder.txt");
        PrintWriter printInorder = new PrintWriter(inorderOut);
        ArrayList<String> inorderstorage = new ArrayList(tree.inorder());
        printInorder.println(inorderstorage);
        printInorder.close();
        return inorderstorage.toString();
    }

    public static String reportPostOrder(BinaryTree tree) throws IOException {

        //attempt to create a directory for the reports to go in
        File reportDir = new File("report");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }

        File treeDir = new File(reportDir.getCanonicalPath(), tree.getName());
        if (!treeDir.exists()) {
            treeDir.mkdir();
        }

        File postorderOut = new File(treeDir.getCanonicalPath(), "postorder.txt");
        PrintWriter printPostorder = new PrintWriter(postorderOut);
        ArrayList<String> postorderstorage = new ArrayList(tree.postorder());
        printPostorder.println(postorderstorage);
        printPostorder.close();
        return postorderstorage.toString();
    }

    public static String explode(BinaryTree tree) {
        final ArrayList<FlatStruct> serialTree = tree.flatten();
        return explode(serialTree, 1);
    }

    private static String explode(ArrayList<FlatStruct> serialTree, int nodeIndex) {
        FlatStruct currentNode = null;
        String outString = "";


        for (int i = 0; i < serialTree.size(); i++) {
            if (serialTree.get(i).getIndex() == nodeIndex) {
                currentNode = serialTree.get(i);
            }
        }

        if (currentNode == null) {
            return "";
        }

        if (nodeIndex != 0) {
            for (int i = nodeIndex; i > 0; i /= 2) {
                outString += "  ";
            }
            outString += "-";
        }

        outString += currentNode.getElement().toString() + "\n";

        outString += explode(serialTree, nodeIndex * 2);
        outString += explode(serialTree, nodeIndex * 2 + 1);

        return outString;
    }

    public static boolean buildTree(File treeFile) throws FileNotFoundException {
        if (treeFile != null) {
            Scanner file = new Scanner(treeFile);
            String input;
            TreeSearch.myTree = new BinaryTree<>();
            if (file.hasNextLine()) {
                input = file.nextLine();
                String[] nodes = input.split(" ");
                for (int i = 0; i < nodes.length; i++) {
                    if (!TreeSearch.myTree.insert(nodes[i])) {
                        TreeSearch.myTree = null;
                        return false;
                    }
                }
            }
            TreeSearch.myTree.setName(treeFile.getName());
            return true;
        } else {
            return false;
        }
    }
}
