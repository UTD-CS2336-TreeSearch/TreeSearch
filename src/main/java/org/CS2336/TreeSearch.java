package org.CS2336;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeSearch {
    BinaryTree<String> myTree = new BinaryTree<String>();
    public static void main(String[] args) {

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