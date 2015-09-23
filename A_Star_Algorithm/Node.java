/*
 * Mariann Szabo-Freund
 * ITCS 3153
 * Assignment 2
 */

public class Node {
	
	private int row, col, f, g, h;
	//private Object type;
	private Node parent;

   
	public Node(int r, int c, Object b){

		row = r;
		col = c;
		parent = null;
	}

	//SETTERS
	public void setF(){
		f = g + h;
	}
	public void setG(int value){
		g = value;
	}
	public void setH(int value){
		h = value;
	}
	public void setParent(Node n){
		parent = n;
	}

	//GETTERS
	public int getF(){
		return f;
	}
	public int getG(){
		return g;
	}
	public int getH(){
		return h;
	}
	public Node getParent(){
		return parent;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	
	public boolean equals(Object O){
		Node n = (Node) O;

		return row == n.getRow() && col == n.getCol();
	}
	
}