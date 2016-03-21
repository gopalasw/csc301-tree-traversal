import java.util.ArrayList;

public class BinaryTree {

	//Node class to use for tree
	private class Node {

		public String val;
		public Node right;
		public Node left;

		public Node(String val) {
			this.val = val;
			this.right = null;
			this.left = null;
		}
	}

	private Node root;
	private ArrayList<Node> arr;

	public BinaryTree() {
		root = null;
		arr = new ArrayList<Node>();
	}

	//Inserts a value into the tree
	public void insert(String val) {
		//If this is the first item inserted
		if(root == null) {
			root = new Node(val);
			arr.add(root);
		} 
		//If there are nodes without two children, insert into them first
		else if(arr.size() > 0) {
			arr.set(0, insert(arr.get(0), val));
			if(arr.get(0).right != null && arr.get(0).left != null) {
				arr.remove(0);
			}
		}
		//Otherwise, insert like normal
		else {
			root = insert(root, val);
		}
	}

	//Helper function to insert recursively
	private Node insert(Node cur, String val) {
		//If there are no further nodes to traverse
		if(cur == null) {
			cur = new Node(val);
			arr.add(cur);
		}
		//Insert in left first if free
		else if(cur.left == null) {
			cur.left = insert(cur.left, val);
		}
		//Otherwise insert in right if free
		else if(cur.right == null) {
			cur.right = insert(cur.right, val);
		}
		//Otherwise continue to left branch of tree
		else {
			cur.left = insert(cur.left, val);
		}
		return cur;
	}

	//Creates an array containing all depth first traversals
	public String[] depthFirst() {
		String[] depthFirstVals =
			{"In order:  " + inOrder(root),
				"Pre order:  "  + preOrder(root),
				"Post order:  "+ postOrder(root)};
		return depthFirstVals;
	}

	//Returns string of in order traversal
	private String inOrder(Node cur) {
		if(cur == null) {
			return "";
		} else {
			return inOrder(cur.left) + cur.val + " " + inOrder(cur.right);
		}
	}

	//Returns string of pre order traversal
	private String preOrder(Node cur) {
		if(cur == null) {
			return "";
		} else {
			return cur.val + " " + preOrder(cur.left) + preOrder(cur.right);
		}
	}

	//Returns string of post order traversal
	private String postOrder(Node cur) {
		if(cur == null) {
			return "";
		} else {
			return postOrder(cur.left) + postOrder(cur.right) + cur.val + " "  ;
		}
	}

	//Returns string of breadth first traversal
	public String breadthFirst() {
		ArrayList<Node> remaining = new ArrayList<Node>();
		remaining.add(root);
		return breadthFirst(remaining);
	}

	//Helper function to traverse recursively
	private String breadthFirst(ArrayList<Node> remaining) {
		//If there are no more elements to traverse
		if(remaining.isEmpty()) {
			return "";
		} else {
			//The current node is the first node in the list
			Node cur = remaining.get(0);
			//Remove this node from the list (as it is already processed)
			remaining.remove(0);
			//If there is a left child, add it
			if(cur.left != null) {
				remaining.add(cur.left);
			}
			//If there is a right child, add it
			if(cur.right != null) {
				remaining.add(cur.right);
			}
			//Continue with rest of the nodes in the list
			return cur.val + " " + breadthFirst(remaining);
		}
	}
}
