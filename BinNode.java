package program2;

/**
* Node for a binary tree of strings.
*
* @author (lj)
* @version (2023)
*/
public class BinNode {
	private String data;
	private BinNode left;
	private BinNode right;
	// add parent 
	private BinNode parent;
	// add height 
	private int height = 1;
	
	private int balance = 0;
	
	public BinNode() {
		data = "";
		left = null;
		right = null;
		parent = null;
		height = 1;
		balance = 0;
}
public BinNode(String d) {
	data = d;
	left = null;
	right = null;
	parent = null;
	balance = 0;
}
public void setData(String d){
	this.data = d;
}

public String getData(){
	return this.data;
}

public void setLeft(BinNode l) {
	this.left = l;
}
public BinNode getLeft() {
	return this.left;
}
public void setRight(BinNode r) {
	this.right = r;
}

public BinNode getRight(){
	return this.right;
}
public void setParent(BinNode p) {
	this.parent = p;
}
public BinNode getParent() {
	return this.parent;
}
public void setHeight(int h) {
	this.height = h;
}
public int getHeight() {
	return this.height;
}

public void setBalance(int b) {
	this.balance = b;
}
public int getBalance() {
	return this.balance;
}


}

