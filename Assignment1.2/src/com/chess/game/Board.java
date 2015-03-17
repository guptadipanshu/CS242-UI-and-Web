package com.chess.game;

import java.util.Vector;

import com.chess.game.pieces.Bishop;
import com.chess.game.pieces.Empty;
import com.chess.game.pieces.King;
import com.chess.game.pieces.Knight;
import com.chess.game.pieces.Pawn;
import com.chess.game.pieces.Queen;
import com.chess.game.pieces.Rook;

public class Board {
	private Piece [][] board;
	private final int RANK=8;
	private final int FILE=8;
	private final int BLACK=-1;
	private final int WHITE=1;
	private final int EMPTY=0;
	/*Initialize a chess board of 8x8*/
	public Board(){
		board=new Piece [RANK][FILE];
		}
	/*Place all the chess piece for game start*/
	public void intialize() {
		// Initialize the black pieces.
		board[0][0]= new Rook(BLACK);
		board[0][1]= new Knight(BLACK);
		board[0][2]= new Bishop(BLACK);
		board[0][3]= new King(BLACK);
		board[0][4]= new Queen(BLACK);
		board[0][5]= new Bishop(BLACK);
		board[0][6]= new Knight(BLACK);
		board[0][7]= new Rook(BLACK);
		for(int file=0;file<8;file++)
			board[1][file]=new Pawn(BLACK);
		
		//Initialize white pieces 
		board[7][0]= new Rook(WHITE);
		board[7][1]= new Knight(WHITE);
		board[7][2]= new Bishop(WHITE);
		board[7][3]= new King(WHITE);
		board[7][4]= new Queen(WHITE);
		board[7][5]= new Bishop(WHITE);
		board[7][6]= new Knight(WHITE);
		board[7][7]= new Rook(WHITE);
		for(int file=0;file<8;file++)
			board[6][file]=new Pawn(WHITE);
		
		// mark the remaining blocks as empty.
		for(int rank=2;rank<6;rank++){
			for(int file=0;file<8;file++)
			{
				board[rank][file]=new Empty(EMPTY);
			}
		}
	}// end of initialization
	/***
	 * Find all possible moves a piece can make
	 * @param playerName
	 * @param curr_x
	 * @param curr_y
	 * @return vector containing moves
	 */
	public Vector<Integer> selectPieceMovement(String playerName,int curr_x,int curr_y){
		Vector<Integer> moves=new Vector<Integer>();
		String getName=board[curr_x][curr_y].getPieceName();
		if(!getName.equals(playerName))
		{
			System.out.println("PLAYER NOT FOUND"+getName);
			return moves;
		}
		else
			board[curr_x][curr_y].selectPieceMovement(board, curr_x, curr_y);
			moves=board[curr_x][curr_y].getMoves();
		return moves;
	}
	/****
	 * Make a move at target position on board
	 * @param pieceName
	 * @param curr_x
	 * @param curr_y
	 * @param target
	 */
	public void makeMovement(String playerName,int curr_x,int curr_y,int target){
		System.out.println("board class move for"+playerName+"at"+curr_x+curr_y+ "to " +target);
		board[curr_x][curr_y].makePieceMove(board, curr_x, curr_y,target);
	}
	/****
	 * Check if the king is in CHECK
	 * @param playerName
	 * @param curr_x
	 * @param curr_y
	 * @return true if the opponent is in CHECK else false
	 */
	public boolean callCheck(String playerName,int curr_x,int curr_y){
		//System.out.println("board class move for"+playerName+"at"+curr_x+curr_y);
		return board[curr_x][curr_y].callCheck(board, curr_x, curr_y);
	}
	/****
	 * Check if the king is in CheckMate
	 * @param Color of the current player
	 * @param curr_x
	 * @param curr_y
	 * @return true if CHECKMATE else false
	 */
	public boolean TestCheckMate(String Color,int curr_x,int curr_y){
		//System.out.println("board class move for"+playerName+"at"+curr_x+curr_y);
		if(Color.equals("BLACK"))
			return board[curr_x][curr_y].getCheckMate(board, 1);
		else
			return board[curr_x][curr_y].getCheckMate(board, -1);
	}
	/****
	 * ADD piece at given rank and file
	 * @param Name
	 * @param color
	 * @param rank
	 * @param file
	 */
	public void addPiece(String Name, int color, int rank, int file) {
		// TODO Auto-generated method stub
		switch(Name){
		case "Rook":	board[rank][file]=new Rook(color);
						break;
		case "Knight":	board[rank][file]=new Knight(color);
						break;
		case "Bishop":	board[rank][file]=new Bishop(color);
						break;
		case "King"  :	board[rank][file]=new King(color);
						break;
		case "Queen" :	board[rank][file]=new Queen(color);
						break;
		case "Pawn"  :	board[rank][file]=new Pawn(color);				
						break;
		}
	}
	/****
	 * Mark all pieces on chess board dead
	 */
	public void addEmptySpaces(){
		for(int rank=0;rank<RANK;rank++){
			for(int file=0;file<FILE;file++){
				board[rank][file]=new Empty(EMPTY);
			}
		}
	}
	public int getPieceColor(int i,int j){
		System.out.println("piece "+board[i][j].getPieceName());
		return board[i][j].getColor();
	}
	public String getPieceName(int i,int j){
		
		return board[i][j].getPieceName();
	}
	
}//class Board end
