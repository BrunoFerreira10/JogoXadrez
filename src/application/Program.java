package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
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
			} catch (ChessException e) {
				System.out.println("\n" + e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("\n" + e.getMessage());
				sc.nextLine();
			}
			
		}
		
		

	}

}
