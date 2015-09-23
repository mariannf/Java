
public class GameBoard {
	private int[] queens;
	private int heuristic;
	private int column; //column that we are checking
	
	public GameBoard() {
		// TODO Auto-generated constructor stub
		//Blank Constructor
	}
	
	
	public GameBoard(int[] queens, int heuristic, int column) {
		super();
		this.queens = queens;
		this.heuristic = heuristic;
		this.column = column;
	}


	public int[] getQueens() {
		return queens;
		
	}
	
	public int getQueenTotal(){
		int counter = 0;
		for(int i = 0; i < queens.length; i++){
			counter++;
		}
		return counter;
	}
	
	public String getQueenList(){
		String theQueens ="";
		for(int i = 0; i < queens.length; i++){
			theQueens += queens[i];
		}
		return theQueens;
	}
	
	public int getHeuristicCalc(){
		int countH = 0;
		int c = 0;
		for(int j = 0; j < 8; j++){
			for(int i = c; i < 8; i++){
				
				if ((queens[j] -(i-j)==queens[i] && j != i) ||(queens[j] + (i-j)==queens[i] && j != i )
						|| (queens[j] == queens[i] && j != i) ){
					countH++;
				}else{
					//Nothing
				}

				}
			//System.out.println();
			c++;
		}
		
		return countH;
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


	public int getColumn() {
		return column;
	}


	public void setColumn(int column) {
		this.column = column;
	}
	
	//A method to move 
	

}
