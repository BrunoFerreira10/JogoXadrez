package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	public abstract Piece[][] possibleMovies();
	
	public Piece(Board board) {
		this.board = board;
	}
	
	protected Board getBoard() {
		return board;
	}

	public Boolean possibleMove(Position position) {
		return false;
	}
	
	public Boolean isThrereAnyPossibleMove(Position position) {
		return false;
	}
	
}
