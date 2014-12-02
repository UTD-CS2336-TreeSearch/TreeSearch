package org.CS2336.btree;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BinaryTree<E extends Comparable<E>> {
    protected BinaryTree.Node<E> root;
    protected int size = 0;
    PriorityQueue<FlatStruct> flatTree;
    private String name = "";

    //Default constructor
    public BinaryTree() {
    }

    //Costruct from an array
    public BinaryTree(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    //Search for value
    public boolean search(E e) {
        BinaryTree.Node<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else
                return true;
        }
        return false;
    }

    //Insert value
    public boolean insert(E e) {
        if (root == null)
            root = new BinaryTree.Node<E>(e);
        else {
            BinaryTree.Node<E> parent = null;
            BinaryTree.Node<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false;
            }
            if (e.compareTo(parent.element) < 0)
                parent.left = new BinaryTree.Node<E>(e);
            else
                parent.right = new BinaryTree.Node<E>(e);
        }
        size++;
        return true; // Element inserted
    }

    //Output inorder
    public ArrayList<E> inorder() {
        ArrayList<E> storage = new ArrayList<E>();
        inorder(root, storage);
        return storage;
    }

    protected void inorder(BinaryTree.Node<E> root, ArrayList<E> storage) {
        if (root == null)
            return;
        inorder(root.left, storage);
        storage.add(root.element);
        inorder(root.right, storage);
    }

    //Output postorder
    public ArrayList<E> postorder() {
        ArrayList<E> storage = new ArrayList<E>();
        postorder(root, storage);
        return storage;
    }

    protected void postorder(BinaryTree.Node<E> root, ArrayList<E> storage) {
        if (root == null)
            return;
        postorder(root.left, storage);
        postorder(root.right, storage);
        storage.add(root.element);
    }

    //Output preorder
    public ArrayList<E> preorder() {
        ArrayList<E> storage = new ArrayList<E>();
        preorder(root, storage);
        return storage;
    }

    protected void preorder(BinaryTree.Node<E> root, ArrayList<E> storage) {
        if (root == null)
            return;
        storage.add(root.element);
        preorder(root.left, storage);
        preorder(root.right, storage);
    }

    //Return the size
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //Clear array
    public void order66() {
        root = null;
        size = 0;
    }

    private BinaryTree.Node<E> walk(BinaryTree.Node n, int index) {
        flatTree.add(new FlatStruct(index, (E) n.element));
        if (n.left != null) {
            walk(n.left, 2 * index);
        }
        if (n.right != null) {
            walk(n.right, 2 * index + 1);
        }
        return n;
    }

    public ArrayList<FlatStruct> flatten() {
        flatTree = new PriorityQueue<FlatStruct>(size + 1);
        walk(root, 1);
        ArrayList<FlatStruct> serialTree = new ArrayList<>();
        while (!flatTree.isEmpty()) {
            serialTree.add(flatTree.poll());
        }
        return serialTree;
    }

    //The core nucleus thingy kajigger
    public static class Node<E extends Comparable<E>> {
        protected E element;
        protected BinaryTree.Node<E> left;
        protected BinaryTree.Node<E> right;

        public Node(E e) {
            element = e;
        }
    }
}
