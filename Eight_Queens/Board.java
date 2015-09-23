
/*
 * Mariann Szabo-Freund
 * ITCS 3123-001 Assignment 1
 * 
 */




import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class Board {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		//List to hold various states of the Boards with various queen placements
		ArrayList<MoveQueen> list = new ArrayList<>();
		
		GameBoard gBoard = new GameBoard();
		MoveQueen mQueen = new MoveQueen();
		
		//Create the board
		Node[][] TheBoard = new Node[8][8];
		
		//Random Number
		Random rand = new Random();
		
		//Initial Game board with 8 randomely placed queens
		int[] randomPos = new int[8];
		int[] newPos = new int[8]; 
		
		for(int y = 0; y < 8; y++){
			
			int randQueen = rand.nextInt(8);
			randomPos[y] = randQueen;

			for(int x = 0; x < 8; x++){
				TheBoard[x][y] = new Node();
				
				//System.out.println(x + " -->x");
				//System.out.println(randomPos[y] + "--> rand pos in x loop");
				if(x == randomPos[y]){
					
					TheBoard[x][y].setaNumber(1);
				}else{
					TheBoard[x][y].setaNumber(0);
				}
				
			}
		}
		
		
		int countH; //test
		int pass = 0;
		
		
		do{
			//System.out.println("---");
			
			
			gBoard.setColumn(1);
			//gBoard.setHeuristic(countH);
			gBoard.setQueens(randomPos);
			
			//System.out.println(gBoard.getQueenList());
			
			//System.out.println("Heuristic from Method: " + gBoard.getHeuristicCalc()); //from method
			//Setting the heuristic and queens in the MoveQueen Object
			mQueen.setQueens(randomPos);
			mQueen.setHeuristic(gBoard.getHeuristicCalc());
			//Add to the ArrayList
			list.add(mQueen);
			
			//Print the required output for the initial board
			//System.out.println("Current h: " + mQueen.getHeuristic());
			//mQueen.getBoard();
			//System.out.println();
			
			
			
			
			
			countH = gBoard.getHeuristicCalc();
			

			//Prints the number of passes for Debug only
			//System.out.println("Pass: " + pass);
			pass++;
			
			//Calculate different states
			for(int y = 0; y < 8; y++){

				for(int x = 0; x < 8; x++){
					
					//Copy the array and add the new position
					System.arraycopy(randomPos, 0, newPos, 0, randomPos.length);
					//System.out.println("Calc for col: " + y + " row: " + x);
					
					
					if(newPos[y] != x){
						newPos[y] = x;
						gBoard.setQueens(newPos);

						//Test review this
						//MoveQueen mQueen = new MoveQueen();
						//mQueen.setQueens(newPos);
						//mQueen.setHeuristic(gBoard.getHeuristicCalc());
						
						mQueen = new MoveQueen(newPos, gBoard.getHeuristicCalc());
						
						list.add(mQueen);
						//Add a line and print the Board
						//System.out.println("");
						//System.out.println("Current h: " + mQueen.getHeuristic());
						//mQueen.getBoard();
						
						pass++;

					}
					else{
						//Nothing
					}
					
					countH = mQueen.getHeuristic();
					
				}
			}
			
		}while(countH != 0 && pass < 64);
		
		int position = 0;
		int[] arrayH = new int[list.size()];
		
		ListIterator<MoveQueen> listIterator = list.listIterator();
		while(listIterator.hasNext()){
			MoveQueen heur = listIterator.next();

			//System.out.println("Heuristics position :" + heur.getHeuristic() + " " + position);
			arrayH[position] = heur.getHeuristic();
			position++;
			
		}
		
		//Now select the lowest state from the ArrayList
		
		//System.out.println("Array List Size :" + list.size());
		//System.out.println("Total Passes : " + pass);
		//System.out.println("Check Array passes : " + arrayH[list.size() - 2]);
		
		//Find the lowest item in the array
		int lowest = arrayH[0];
		int pos = 0;
		int neighbors = 0;
		for(int i = 1; i<arrayH.length; i++){
			if (arrayH[i] < lowest){
				lowest = arrayH[i];
				//Keep th i value for the position in list/array
				pos = i;
				neighbors++;
			}
		}
		
		//System.out.println("Lowest Heuristic is : " + lowest + " at position :" + pos);
		System.out.println("Current h: " + lowest);
		//System.out.println();
		list.get(pos).getBoard();
		System.out.println("Neighbors found with lower h: " + neighbors);
		System.out.println("Setting new current state");
		
	}

}//END