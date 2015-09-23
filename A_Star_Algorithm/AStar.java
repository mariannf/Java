/*
 * Mariann Szabo-Freund
 * ITCS 3153
 * Assignment 2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AStar extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5948355970838865283L;
	private final int WINDOW_WIDTH = 1150;
	private final int WINDOW_HEIGHT = 600;
	private static JButton[][] button  = new JButton[15][15];


	public AStar ()
	{
		super ("Board");
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new GridLayout(15,15));

		//Create an array that contains the button name 
		//it will loop 225 times and will fill the value for the name

		String[][] btnName = new String[15][15];
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				btnName[i][j] = "btn" + i+" "+j;
			}
		}


		//create 225 tiles
		for(int i = 0; i < button.length; i++){

			for(int j = 0; j < button[i].length; j++){

				//JButton button = new JButton(btnName[i]);
				button[i][j] = new JButton(i + ", "+(j));

				if(Math.random() < 0.1){ 

					button[(i)][j].setBackground(Color.black);
					add(button[i][j]);
				}else{
					button[i][j].addActionListener(this); //add an action listener to the current button
					add(button[i][j]);

				}
			}
		}

		setVisible(true);
	}


	public static void main(String args[]) throws Exception{

		//Scanner k = new Scanner(System.in);
		int startRow, startCol, endRow, endCol;
		Node StartNode;
		Node EndNode;

		new AStar();

		//User Input
		String startRowStr = JOptionPane.showInputDialog("Enter Starting  Row: ");
		startRow = Integer.parseInt(startRowStr);

		String startColStr = JOptionPane.showInputDialog("Enter Starting  Column: ");
		startCol = Integer.parseInt(startColStr);

		button[startRow][startCol].setBackground(Color.green);

		String endRowStr = JOptionPane.showInputDialog("Enter Ending  Row: ");
		endRow = Integer.parseInt(endRowStr);

		String endColStr = JOptionPane.showInputDialog("Enter Ending  Column: ");
		endCol = Integer.parseInt(endColStr);

		button[endRow][endCol].setBackground(Color.red);


		StartNode = new Node(startRow, startCol, button[startRow][startCol]);
		EndNode = new Node(endRow, endCol, button[endRow][endCol]);

		StartNode.setG(0);
		StartNode.setH(getH(StartNode, EndNode));
		System.out.print("h= "+ getH(StartNode, EndNode));
		System.out.println();
		StartNode.setF();


		System.out.println("Start point is : " + startRow+" "+startCol + "  End point is : " + endRow+" "+endCol);
		solve(StartNode, EndNode, button);

		button[startRow][startCol].setBackground(Color.green); //TEST
		
		//Test to restart game
		/*String keepPlay = JOptionPane.showInputDialog("Would you like to continue playing Type [Y][N]: ");
		if(keepPlay.equals("Y")){
			new AStar();
		}else{
			//Close the program
		}*/
	}


	public static void solve(Node start, Node end, JButton[][] button){

		boolean search = true;

	
		ArrayList<Node> openList = new ArrayList<Node>();	 //visited nodes
		ArrayList<Node> closedList = new ArrayList<Node>();	 //unvisited nodes


		//	openList.add(start);
		openList.add(start);

		//Node n = null;
		while(search){

			Node n = openList.remove(0);
			//n = openList.remove(0);

			//check for goal state
			if(n.equals(end)){
				search = false;
				
				while(!n.equals(start)){
				
				
						n = n.getParent();
					button[n.getRow()][n.getCol()].setBackground(Color.pink); //Set the cell/button to pink because it is a path
					
				}
				
			}else{

				//check neighbors
				int r = n.getRow();
				int c = n.getCol();
				
				for(int i = r - 1; i <= r + 1; i++){
					for(int j = c - 1; j <= c + 1; j++){

						if(i >= 0 && i < 15 && j >= 0 && j < 15 && (i != r || j != c) && (button[i][j].getBackground() != Color.BLACK)){
							Node m = new Node(i, j, button[i][j]);
							
							m.setParent(n);

							int newG = 10;
							if(Math.abs(i - r) + Math.abs(j - c) == 2){
								newG = 14;
							}
							m.setG(n.getG() + newG);
							m.setH(getH(m, end));
							m.setF();

							if(inList(m, closedList) == null){
								Node q = inList(m, openList);
								if(q == null){
									openList.add(m);

								//button[m.getRow()][m.getCol()].setBackground(Color.yellow); //Colors in yellow nodes that were explored during the process!

								}else{
									if(m.getG() < q.getG()){
										q.setG(m.getG());
										q.setParent(n);

									}
								}
							}
						}
					}
				}
				
				sortList(openList);
				closedList.add(n);
			}
		}//End while
		
	}


	//Sort the list

	public static void sortList(ArrayList<Node> list){
		int lowIndex;
		Node lLow; 

		for(int i = 0; i < list.size(); i++){
			lowIndex = i;
			for(int j = i; j < list.size() - 1; j++){
				if(list.get(j + 1).getF() < list.get(lowIndex).getF()){
					lowIndex = j + 1;
				}
			}
			Node value = list.get(i);
			lLow = list.get(lowIndex);
			list.set(i, lLow);
			list.set(lowIndex, value);
		}//End first loop
	}
	
	

	//Get Heuristics
	public static int getH(Node s, Node g){
		int row, col, heuristic;
		int sRow = s.getRow(), gRow = g.getRow(), sCol = s.getCol(), gCol = g.getCol();
		row = Math.abs(sRow - gRow)*10; //times 10 for horizontal and vertical steps while 14 is the path
		col = Math.abs(sCol - gCol)*10;
		heuristic = row + col;
		return heuristic;

	}
	
	//Check if it is in the open list
	public static Node inList(Node n, ArrayList<Node> list){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).equals(n)){
				return list.get(i);
			}
		}
		return null;
	}	


	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object src = evt.getSource();
		if (src == button[0][0]) {
			//First button actions
			System.out.println("You clicked Button 0");
			button[0][0].setBackground(Color.green);
		} else if (src == button[1][1]) {
			System.out.println("You clicked Button 1");
		}

	}
}
