package org.CS2336;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class BinaryTree<E extends Comparable<E>> {
    protected Node<E> root;
    protected int size = 0;

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
        Node<E> current = root;
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
            root = new Node<E>(e);
        else {
            Node<E> parent = null;
            Node<E> current = root;
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
                parent.left = new Node<E>(e);
            else
                parent.right = new Node<E>(e);
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

    protected void inorder(Node<E> root, ArrayList<E> storage) {
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

    protected void postorder(Node<E> root, ArrayList<E> storage) {
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

    protected void preorder(Node<E> root, ArrayList<E> storage) {
        if (root == null)
            return;
        storage.add(root.element);
        preorder(root.left, storage);
        preorder(root.right, storage);
    }

    //The core nucleus thingy kajigger
    public static class Node<E extends Comparable<E>> {
        protected E element;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E e) {
            element = e;
        }
    }

    //Return the size
    public int getSize() {
        return size;
    }

    //Clear array
    public void order66() {
        root = null;
        size = 0;
    }

    public void setSize(int size) {
        this.size = size;
    }

    PriorityQueue<flatStruct> flatTree;
    private Node<E> walk(Node n, int index) {
        flatTree.add(new flatStruct(index, (E) n.element));
        if(n.left != null) {
            walk(n.left, 2 * index);
        }
        if(n.right != null) {
            walk(n.right, 2 * index + 1);
        }
        return n;
    }

    public ArrayList<flatStruct> flatten() {
        flatTree = new PriorityQueue<flatStruct>(size+1);
        walk(root, 1);
        ArrayList<flatStruct> serialTree = new ArrayList<>();
        while(!flatTree.isEmpty()) {
            serialTree.add(flatTree.poll());
        }
        return serialTree;
    }

    class flatStruct implements Comparable<flatStruct> {
        int index;
        E element;
        public flatStruct(int index, E element) {
            this.index = index;
            this.element = element;
        }

        public int compareTo(flatStruct o) {
            return index - o.index;
        }

        public String toString() {
            return "("+element.toString()+":"+index+")";
        }
    }
}
