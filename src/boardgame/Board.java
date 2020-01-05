package boardgame;

public class Board {
	
	private int rows;
	private int colunms;
	
	private Piece[][] pieces;
	
	public Board(int rows, int colunms) {
		this.rows = rows;
		this.colunms = colunms;
		this.pieces = new Piece[rows][colunms];
	}

	public int getRows() {
		return rows;
	}

	public int getColunms() {
		return colunms;
	}	
	
	public Piece piece (int row, int column) {
		return pieces[row][column];
	}
	
	public Piece piece (Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece() {
		return null;
	}
	
	public Boolean positionExists(Position position) {
		return null;
	}
	
	public Boolean thereIsAPiece(Position position) {
		return null;
	}
}
