package game;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		// create the Scanner object to accept inputs
		Scanner input = new Scanner(System.in);
		
		
		Board b = new Board();
		Player players[] = new Player[2];
		int gameCount = 1;
		
		// set up the players
		for(int i = 0; i < 2; ++i) {
			System.out.println("Player " + (i + 1));
			
			System.out.print("What is your name?: ");
			String name = input.nextLine();
			System.out.print("Which character do you want to use?: ");
			char piece = input.next().charAt(0);
			
			players[i] = new Player(name, 0, piece);
			System.out.println();
			input.nextLine();
		}
		
		Player currentPlayer;
		int currentPlayerIndex = 0;
		
		// this is the game loops
		while(!b.isGameOver()) {
			System.out.println("\n\n--------------------------------------------------------");
			System.out.println("            CURRENTLY PLAYING :: GAME #" + gameCount);
			System.out.println("--------------------------------------------------------\n"); 
			b.showBoard();
			
			currentPlayer = players[currentPlayerIndex];
			
			System.out.println("Player [" + currentPlayer.getName() +"] - Where do you want to put your piece?");
			int row = input.nextInt();
			b.addPieceToBoard(currentPlayer.getPiece(), row - 1, currentPlayer);
			
			if(currentPlayerIndex == 1) { currentPlayerIndex = 0; }
			else { currentPlayerIndex = 1; }
			
			if(b.isGameOver()) {
				char ans;
				do {
					System.out.println("Do you want to play again? ");
					ans = input.next().toLowerCase().charAt(0);
				} while(ans != 'y' && ans != 'n');
				
				if(ans == 'y') { 
					b.initBoard();
					gameCount++;
				}
			}
		}
		
		// close the input
		input.close();
	}

}
