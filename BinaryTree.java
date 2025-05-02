package program2;

/**
* An ordered binary tree class.
* TO DO:
* 1) Complete the methods indicated.
* 2) Add a remove method that eliminates the node of a given value
* (parameter = data to find and kill; return = void)
*
* @author (Mercedes VanArsdale)
* @version (2025)
*/
public class BinaryTree {
	public BinNode root;
	/**
	 * default constructor
	 */
	public BinaryTree() {
		root = null;
	}
	public BinNode getRoot() {
		return this.root;
	}
	/* Function to check if tree is empty */
	public boolean isEmpty() {
		if (root == null ) {
			return true;
		} else {
			return false;
		}
	}
/* Functions to insert data */
public void insert(String data) {
	
	this.root = insert(this.root, data);
	//search(data);
	
	return;
}
/* Function to insert data recursively */
// Referenced: https://www.baeldung.com/java-binary-tree
private BinNode insert(BinNode node, String data) {
	// Initialize variable
	//BinNode parentNode = node;
	// null position in tree turns into a something y'know
	if (node == null) {
		// 
		node = new BinNode(data);
		// set roots height 
		//node.setHeight(1);
	} else {
		if (data.compareTo(node.getData()) < 0) {
        	// Recursively implement a node lesser than the current data 
			node.setLeft(insert(node.getLeft(), data));
           
            // set new nodes parent to current node
            node.getLeft().setParent(node);
            
            // trickle up tree to fix height 
            updateTreeHeights(node);
            
            //node = node.getLeft();
            
            
        } else if (data.compareTo(node.getData()) > 0){
        	// Recursively implement a node greater than the current data 
            node.setRight(insert(node.getRight(), data));

            // set new nodes parent to current node
            node.getRight().setParent(node);
            
            // reset the trees height 
            updateTreeHeights(node);
            
            // move node to most recently inserted node
         	//node = node.getRight();
        } else {
        	// node has already been inserted 
        	return node;
        }
	}
	return node;
}
//Function to update height of a node from chatGPT
public int updateHeight(BinNode node) {
    if (node == null) {
        return 0;  // Base case: if the node is null, return height 0
    }

    // Recursively update the heights of left and right subtrees
    int leftHeight = updateHeight(node.getLeft());
    int rightHeight = updateHeight(node.getRight());

    // Update the height of the current node
    node.setHeight(1 + Math.max(leftHeight, rightHeight));

    return node.getHeight();  // Return the height of the current node
}

// You can call this function to start the height update from the root
public void updateTreeHeights(BinNode root) {
    updateHeight(this.root);
}
// sets the balanceFactor of every subtree from newly inserted leaf - up
/* Function to count number of nodes */
public int countNodes() {
	return countNodes(this.root);
}
/* Function to count number of nodes recursively */
private int countNodes(BinNode r) {
	int count = 0;
	
	// base case
	if (r == null) {
		return 0;
	} 
	// count nodes to the left and right
	count = countNodes(r.getLeft()) + countNodes(r.getRight()) + 1;

	// return amount of nodes
	return count;	
}
/* Function to search for an element */
public boolean search(String val) {
	return search(this.root, val);
}
/* Function to search for an element recursively */
private boolean search(BinNode root, String val) {
	// binary tree is empty 
	if (root == null) {
		// node will never be found
		return false;
	}
	// if the nodes data is equal to value being searched for 
	if (root.getData().equals(val)) {
		// element was not found
		return true;
	} 
	
	// comparesTo from chatGPT 
	if (val.compareTo(root.getData()) < 0) {
		// smaller so search left 
		return search(root.getLeft(), val);
	} else {
		// larger so search right 
		return search(root.getRight(), val);
	}
}
/* Function for in-order traversal */
public void inorder() {
	inorder(this.root);
}
private void inorder(BinNode r) {
	if (r != null) {

		inorder(r.getLeft());
		System.out.print(r.getData() +" ");
		inorder(r.getRight());
	}
}
/* Function for pre-order traversal */
public void preorder() {
	preorder(root);

}
private void preorder(BinNode r){
	if (r != null) {
		System.out.print(r.getData() +" ");
		preorder(r.getLeft());
		preorder(r.getRight());
	}
}
/* Function for post-order traversal */
public void postorder() {
	postorder(root);
}
private void postorder(BinNode r) {
	if (r != null) {
		postorder(r.getLeft());
		postorder(r.getRight());
		System.out.print(r.getData() +" ");
	}
}


/* Functions to insert data */
public void eliminate(String Data) {
	eliminate(this.root, Data);
}

/* Function to eliminate data recursively */
private void eliminate(BinNode r, String data) {
	// Initialize variable
	BinNode parentNode = r;
	// code from: https://www.digitalocean.com/community/tutorials/binary-search-tree-bst-search-insert-remove
	// traverse to find node needing to be deleted
	while (r != null) {
        // node found
		if (r.getData().equals(data)) {
            break;
        }
        // parent of the node getting deleted 
        parentNode = r;
        // data is less than current nodes data 
        if (data.compareTo(r.getData()) < 0) {
        	r = r.getLeft();
        } else {
        	// data is more than current nodes data 
        	r = r.getRight();
        }
	}
	
	// node not found
	if (r == null) {
		return; 
	}
	
	// original node that's being deleted 
	BinNode originNode = r;
	
	// best case: no children just kill the node
	if (r.getRight() == null && r.getLeft() == null) {
		// if node needing to be deleted is to left of parent
		if (parentNode.getLeft() == r) {
			// set left to null
			parentNode.setLeft(null);
		} else {
			// set right to null if it's not left
			parentNode.setRight(null);
		}
			
	// left case: only has left child 
	} else if (r.getLeft() != null && r.getRight() == null) {
		// set up the parentNode
		parentNode = r;
		
		// go left once 
		r = r.getLeft();
		// check for children 
		if (r.getRight() != null) {
			// go right as far as possible 
			while (r.getRight() != null) {
				// update parentNode 
				parentNode = r;
				// go further right 
				r = r.getRight();
				// no more children
				if (r.getRight() == null) {
					// break from loop
					break;
				}
			}
			// put the value of the current nodes data into originals
			originNode.setData(r.getData());
			// deleted 
			parentNode.setRight(parentNode.getRight().getLeft());
		// if has no children to check
		} else {
			// put the value of the current nodes data into originals
			originNode.setData(r.getData());
			// delete leaf
			originNode.setLeft(r.getLeft());
		}	
	// right case: only has a right child 
	} else if (r.getRight() != null && r.getLeft() == null) {
		// set parent node variable
		parentNode = r;
		
		// go right once 
		r = r.getRight();
		// its child has an important child 
		if (r.getLeft() != null) {
			// go left as far as possible 
			while (r.getLeft() != null) {
				parentNode = r;
				r = r.getLeft();
				// reached last
				if (r.getLeft() == null) {
					break;
				}
			}
			// swap the data of node getting deleted 
			originNode.setData(r.getData());
			// delete the leaf with that value 
			parentNode.setLeft(null);
		// children has no important children  
		} else {
			// replace original data 
			originNode.setData(r.getData());
			// delete leaf 
			originNode.setRight(r.getRight());
		}
		// worst case: both children exist
	} else { 
		// set parentNode variable 
		parentNode = r; 		
		// go right once 
		r = r.getRight();
		// it's children has children
		if (r.getLeft() != null) {
			// go left as far as possible 
			while (r.getLeft() != null) {
				parentNode = r; 
				r = r.getLeft();
				// break if no more nodes
				if (r.getLeft() == null) {
					break;
				}
			}
			// replace original node with new found data
			originNode.setData(r.getData());
			// set new left 
			parentNode.setLeft(r.getRight());
		// its child is a leaf 
		} else {
			// replace original nodes data 
			originNode.setData(r.getData());
			// delete leaf 
			originNode.setRight(null);
		}
	}
}
}