/*
 * Mariann Szabo-Freund
 * 
 * This class keeps the states of the board when the queens are
 * moved up and down the colums
 * later to be used to find the move with the lowest heuristic
 * 
 */


public class MoveQueen {
	//Fields

	private int[] queens;
	private int heuristic;
	
	//Null constructor
	public MoveQueen(){
		//Null constructor
	}
	
	
	public MoveQueen(int[] queens, int heuristic) {
		super();
		this.queens = queens;
		this.heuristic = heuristic;
	}



	//Setters and Getters
	public int[] getQueens() {
		return queens;
	}

	public void setQueens(int[] queens) {
		this.queens = queens;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	
	//Print the board back when call in 0 and 1 where 1 is where the queens are
	public void getBoard(){
		//Print the Board back in 0 and 1 mode
		for(int x = 0; x < 8; x++){
			for (int y = 0; y < 8; y++){
				if(x == queens[y]){
					System.out.print("1 ");
				}
				else{
					System.out.print("0 ");
				}
			}
			System.out.println(); 
		}
		
	}
	
}//END
