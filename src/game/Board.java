package game;

public class Board {
	// the array which is null
	private char[][] board; 
	private int row = 7;
	private int col = 7;
	private boolean gameOver;
	
	public Board() {
		board = new char[row][col];
		initBoard();
	}
	
	public void initBoard() {
		for(int i = 0; i < row; ++i) {
			for(int j = 0; j < col; ++j) {
				board[i][j] = '*';
			}
		}
		this.gameOver = false;
	}
	
	public boolean isGameOver() { 
		return gameOver; 
	}
	
	public void gameOver(Player winner) {
		System.out.println("\n\n--------------------------------------------------------");
		System.out.println("                    GAME OVER -- WINNNER");
		System.out.println("--------------------------------------------------------\n"); 
		
		this.showBoard();
		winner.setScore(winner.getScore() + 1);
		
		System.out.println("--------------------------------------------------------"); 
		System.out.println("  Player Name: " + winner.getName());
		System.out.println("  Player Score: " + winner.getScore());
		System.out.println("--------------------------------------------------------"); 
		
		gameOver = true;		
	}
	
	public void addPieceToBoard(char piece, int col, Player currentPlayer) {
		int row = this.row - 1; // this sets the pointer to the bottom row 
		boolean isAdded = false; // this is the flag to check if the items is added
		
		while(!isAdded && row >= 0) {
			if(board[row][col] == '*') { // if there is nothing item in the desired position
				board[row][col] = piece; // add the item to the board
				isAdded = true; // and mark the item as added
			}
			row--; // else move on to the row above the current one
		}
		
		this.checkBoard(piece, currentPlayer); // check if the piece that was added is the winning piece
	}
	
	public boolean checkBoard(char piece, Player currentPlayer) {
		// this checks if the player has won
		if(this.checkVertically(piece) || this.checkHorizontally(piece) || this.checkDiagonally(piece)) {
			this.gameOver(currentPlayer); // if they did, the game is over
			return true;
		}
		return false;
	}
	
	public boolean checkHorizontally(int piece) {
		int i, j, count = 0;
		for(i = 0; i < row; ++i) {
			count = 0; // resets the counter for every row
			for(j = 0; j < col; ++j) {
				count++; // this assume that every piece is a player piece and increment the counter
				if(board[i][j] != piece) { count = 0; } // this resets the counter every time a non-player piece is seen
			}
			
			if(count == 4) { break; } // if you found a winner, no need to check the rest
		}
		
		if(count == 4) { return true; } // game is over
		return false; // or is it?
	}
	
	public boolean checkVertically(int piece) {
		int i, j, count = 0;
		// please refer to the checkHorizontally function because it is literally the same thing
		// ... however the row and col are reversed
		for(i = 0; i < col; ++i) {
			count = 0;
			for(j = 0; j < row; ++j) {
				count++; 
				if(board[j][i] != piece) { count = 0; }
			}
			if(count == 4) { break; }
		}
		
		if(count == 4) { return true; }
		
		return false;
	}
	
	public boolean checkDiagonally(int piece) {
		int i, j, count = 0;
		
		// this was a little bit tricky
		for(i = 0; i < row; i++) {
			for(j = 0; j < col; j++) {
				if(board[i][j] == piece) { // if your piece is found on the board
					
					// check either the diagonal to the bottom right or bottom left
					int tempRow = i, tempCol = j; // my temporary row and col for the diagonal
					
					// I need to make sure they don't surpass the bound of the board
					if(i > row) { tempRow++; } 
					if(j > col) { tempCol++; }
					 
					count = 1; // counter for check if all for 4 piece where found
					do {
						count++; // assumes that all pieces to the bottom right of the current piece matches the piece we are looking for
						if(board[tempRow][tempCol] != piece) { count = 1; } // this resets the counter if that wasn't the case
						
						tempRow++;tempCol++;  // continue to the next diagonal piece
					} while(count != 4 && tempRow < row && tempCol < col); 
					
					if(count == 4) { break;	} // if the four pieces were found on these diagonals, there is no need to check the other
					
					// otherwise check the other diagonals by first resetting some key values
					count = 1;
					tempRow = i; tempCol = j;
					
					if(i > row) tempRow++;
					if(j > col) tempCol++;
					
					// do the same things as before just to the left instead of the right
					do {
						count++;
						if(board[tempRow][tempCol] != piece) { count = 1; }
						
						tempRow++;tempCol--;
					} while(count != 4 && tempRow < row && tempCol > 0); 
					
					if(count == 4) {
						System.out.println("Completed - dia right");
					}
				}
			}
			if(count == 4) { break; }
		}
		
		if(count == 4) { return true; }
		return false;
	}
	
	public void showBoard() {
		for(int i = 0; i < row; ++i) {
			System.out.print("\t\t |");
			for(int j = 0; j < col; ++j) {
				System.out.print( " " + board[i][j] + " ");
			}	
			System.out.println("|");
		}
		System.out.println("\t\t -----------------------");
		System.out.println("\t\t   1  2  3  4  6  7  8  \n");
	}
}
