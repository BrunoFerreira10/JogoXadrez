package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> capturedPieces = new ArrayList<ChessPiece>();
		
		while(!chessMatch.isCheckmate()) {
			
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, capturedPieces);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition sourceChessPosition = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(sourceChessPosition);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition targetChessPosition = UI.readChessPosition(sc);
				
				ChessPiece capturedChessPiece = chessMatch.performChessMove(sourceChessPosition, targetChessPosition);
				if(capturedChessPiece != null)
					capturedPieces.add(capturedChessPiece);
			} catch (ChessException e) {
				System.out.println("\n" + e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("\n" + e.getMessage());
				sc.nextLine();
			}
			
		}
		
		UI.clearScreen();
		UI.printMatch(chessMatch, capturedPieces);
		
	}

}
