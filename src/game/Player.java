package game;

public class Player {
	private String name;
	private int score;
	private char piece;
	
	public Player(String name, int score, char piece) {
		this.name = name;
		this.score = score;
		this.piece = piece;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public char getPiece() {
		return piece;
	}

	public void setPiece(char piece) {
		this.piece = piece;
	}
}
