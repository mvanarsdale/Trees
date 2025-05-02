package program2;

/**
* An AVL tree class
* 
* @author (Mercedes VanArsdale)
* @version (2025)
*/

public class AVL_Tree extends BinaryTree {
	//private BinNode root;
    
 	// constructor
    public AVL_Tree() {
        // call constructor of parent (binary tree)
    	super(); 
    }
    
    /* Functions to insert data */
    @Override
    public void insert(String data) {
    	// calling parents method
    	super.insert(data);
    	
    	// insert AVL
    	this.root = insertAVL(this.root, data);
    }
    // AVL insert logic
    private BinNode insertAVL(BinNode node, String data) {
    	// find the balanceFactor of the nodes
    	balancedFactor(node);
    	// AVL specific insertion logic 
    	node = findBalance(node);
    	
    	return node;
    }
	// sets balanced factors of nodes in tree
    private void balancedFactor(BinNode node) {
    	setBalanceFactors(node);
   }
    // code referenced from ChatGPT 
    private void setBalanceFactors(BinNode node) {
    	if (node == null) return;

        // Initialize variables
        int leftHeight;
        int rightHeight;

        // set height manually if null
        if (node.getLeft() == null) {
        	leftHeight = 0;
        } else {
        	// find nodes left side height 
        	leftHeight = node.getLeft().getHeight();
        }
        // set height manually if null
        if (node.getRight() == null) {
        	rightHeight = 0;
        	
        } else {
        	// find nodes right side height 
        	rightHeight = node.getRight().getHeight();
        }
        // debugging print
        //System.out.println("Node: " + node.getData() + " | Left Height: " + leftHeight + " | Right Height: " + rightHeight);

        // set nodes balanced based on height 
        node.setBalance(rightHeight - leftHeight);
        
        // update parents balance 
        setBalanceFactors(node.getParent());
        //setBalanceFactors(node.getRight());
        System.out.println("Updating balance for node: " + node.getData());
    }
    // determines if rotations are needed 
    private BinNode findBalance(BinNode node) {
    // if the node is the root 
    if (node.getParent() != null) {
    	while (node.getParent() != null) {
    		// move up in the tree
    		node = node.getParent();
    		// check the subtree 
    		balanceChecker(node);
    	}
    } else {
    	// check balance of root
		balanceChecker(node);
    	}
	return node;
    }
    // finding what rotation the subtree needs (if any)
    private BinNode balanceChecker(BinNode node) {
    	// Unbalanced - left cases
    	if (node.getBalance() < -1) {
    		// double left rotation 
    		if (node.getLeft() == null || node.getLeft().getBalance() < 0 ) {
    			System.out.println("left left");
    			return rotateRight(node);	
    		} else {
    			// left right
    			System.out.println("left right");
    			if (node.getLeft() != null) {
    				node.setLeft(rotateLeft(node.getLeft()));
    			}
    			return rotateRight(node);
    		}
    	}
    	// Unbalanced - right cases
    	else if (node.getBalance() > 1) {
    		// double right rotation
    		if (node.getRight() == null || node.getRight().getBalance() > 0 ) {
    			System.out.println("right right");
    			return rotateLeft(node);
    		} else {
    			// right left rotation
    			System.out.println("right left");
    			if (node.getRight() != null) {
    				node.setRight(rotateRight(node.getRight()));
    			}
    			return rotateLeft(node);
    		}
    	// balanced subtree rotations are not needed
    	} else {
			System.out.println("balanced checked");
    	}
		return node;
    }
    // right rotation
    private BinNode rotateRight(BinNode node) {
    	// newRoot for subtree change back to left 
    	BinNode newRoot = node.getLeft();

    	// move newRoots child to its parents child
    	newRoot.getParent().setLeft(newRoot.getRight());
    	
    	// update height if needed
    	if (newRoot.getParent().getLeft() != null) {
    		newRoot.getParent().getLeft().setParent(newRoot.getParent());
    	}
    	// point newRoot's left pointer to its parent 
    	newRoot.setRight(newRoot.getParent());
    	
    	// update parents
    	newRoot.setParent(newRoot.getParent().getParent());
    	newRoot.getRight().setParent(newRoot);
    	
    	// update heights not working - from Binary Tree
    	updateTreeHeights(node);

		return newRoot;
    	
    }
    // left rotation 
    private BinNode rotateLeft(BinNode node) {
    	// newRoot for subtree
    	BinNode newRoot = node.getRight(); 
    	
    	// move newRoots child to its parents child
    	newRoot.getParent().setRight(newRoot.getLeft());
    	
    	// update height if needed
    	if (newRoot.getParent().getRight() != null) {
    		newRoot.getParent().getRight().setParent(newRoot.getParent());
    	}
    	// point newRoot's left pointer to its parent 
    	newRoot.setLeft(newRoot.getParent());
    	
    	// update parents
    	newRoot.setParent(newRoot.getParent().getParent());
    	newRoot.getLeft().setParent(newRoot);

    	// update heights not working - from Binary Tree
    	updateTreeHeights(node);

		return newRoot;
    }
}