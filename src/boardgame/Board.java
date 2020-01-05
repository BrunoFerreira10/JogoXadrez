package boardgame;

public class Board {
	
	private int rows;
	private int colunms;
	
	private Piece[][] pieces;
	
	public Board(int rows, int colunms) {
		
		if(rows < 1 || colunms < 1)
			throw new BoardException("Error creating board: it must have at least 1 row and one column");
			
			
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
	
	public Piece getPiece (int row, int column) {	
		
		if(!positionExists(row, column))
			throw new BoardException("Position do not exists!");
		
		return pieces[row][column];
		
		/*if(positionExists(row, column))
			return pieces[row][column];
		else 
			return null;*/
	}
	
	public Piece getPiece (Position position) {
		return getPiece(position.getRow(),position.getColumn());
	}

	public void placePiece(Piece piece, Position position) {
		
		if(thereIsAPiece(position))
			throw new BoardException("There already a piece at position "+position);
		
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		
		if(!thereIsAPiece(position))
			return null;
		else {
			Piece removedPiece = getPiece(position);
			removedPiece.position = null;
			pieces[position.getRow()][position.getColumn()] = null;
			return removedPiece;
		}
	}
	
	public Boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	private  Boolean positionExists(int row, int column) {				
		return row >= 0 && row < this.rows && column >= 0 && column < this.colunms;
	}
	
	public Boolean thereIsAPiece(Position position) {
		return getPiece(position) != null;
	}
	
}
