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
				}
				else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				}
				else
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
	public void inorder() {
		inorder(root);
	}
	protected void inorder(Node<E> root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}

	//Output postorder
	public void postorder() {
		postorder(root);
	}
	protected void postorder(Node<E> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}

	//Output preorder
	public void preorder() {
		preorder(root);
	}
	protected void preorder(Node<E> root) {
		if (root == null)
			return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
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
	public void fire() {
		root = null;
		size = 0;
	}
	
	//Create tree from tree
	public void buildFromFile() {
		
	}
	
	//Print tree to file
	public void printToFile() {
		
	}
	
	//Display tree
	public void displayTree() {
		
	}
}
