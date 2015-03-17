package com.chess.game;

import java.util.Vector;

abstract public class Piece {
	private int color=0;
	private int pieceId=0;
	private int alive=0;
	private int checkKing=0;
	private Vector<Integer> validMoves;
	protected final int RANK=8;
	protected final int FILE=8;
	/**
	 * Initialize the piece
	 * @param setColor
	 * @param setId is the pieceId
	 */
	public void setPiece(int setColor,int setId) {
		color=setColor;
		pieceId=setId;
		alive=1;
		checkKing=0;
		validMoves=new Vector<Integer>();
	}
	/**
	 * Mark a piece dead
	 */
	public void killPiece(){
		alive=0;
		color=0;
	}
	/**
	 * Mark the king under check
	 */
	protected void checkKing(){
		checkKing=1;
	}
	/**
	 * Mark player color as -1 for black,0 for no player,1 for white 
	 * @param playerColor
	 */
	protected void setColor(int playerColor) {
		color=playerColor;
	}
	/**
	 * Mark King not under check
	 */
	protected void unCheckKing(){
		checkKing=1;
	}
	/**
	 * Return true if player is alive
	 * @return
	 */
	public int pieceStatus(){
		return this.alive;
	}
	/**
	 * Get player color
	 * @return -1 for black,1 for white else 0
	 */
	public int getColor(){
		return this.color;
	}
	/**
	 * @return piece id
	 */
	public int getPiece(){
		return this.pieceId;
	}
	/**
	 * Return a string containing player name
	 * @return palyerName
	 */
	public String getPieceName(){
		String name="";
		switch(pieceId){
		case 0 : name="Empty";
				 break;
		case 1 : name= "Rook";
				 break;
		case 2:  name= "Knight";
				 break;
		case 3 : name="Bishop";
		 		 break;
		case 4 : name= "King";
				 break;
		case 5:  name= "Queen";
		 		 break; 
		case 9:  name="Pawn";
				 break;
		}
		return name;
	}
	/**
	 * Add a move as valid
	 * @param position
	 */
	protected void insertMove(int position){
		//System.out.println(position);
		validMoves.add(position);
	}
	/**
	 * Mark all moves empty
	 */
	public void clearMoves(){
		validMoves.clear();
	}
	/**
	 * Get moves player can make
	 * @return vector containing moves
	 */
	public Vector<Integer> getMoves(){
		return this.validMoves;
	}
	/**
	 * Check if the given move is valid
	 * @param target
	 * @return true if valid move else false
	 */
	protected boolean checkMove(int target){
	  return validMoves.contains(target);
	}
	/**
	 * Print the vector containing moves
	 * @param print
	 */
	private void printVector(Vector<Integer> print){
		for(int i=0;i<print.size();i++)
			System.out.println(" "+print.elementAt(i));
	}
	/****
	 * Get the king position
	 * @param board
	 * @param currX
	 * @param currY
	 * @return king position
	 */
	protected int getKingPosition(Piece [][] board,int currX,int currY){
		for(int i=0;i<RANK;i++){
			for(int j=0;j<FILE;j++){
				if(i!=currX && j!=currY){
					if(board[i][j].getColor()!=0 &&
						this.getColor()!=board[i][j].getColor() &&
						board[i][j].getPieceName().equals("King"))
								return i*RANK+j;
				}	
			}
		}
		return -1;
	}
	/***
	 * Check if King is in checkMate. First check if all moves
	 * made by other king are same as current player moves. Then check if a other
	 * player other chess piece can stop check Mate
	 * @param board
	 * @param color
	 * @return true if opponent is CheckMate else false
	 */
	protected boolean getCheckMate(Piece[][] board,int color){
		int king_position=this.getKingPosition(board, -1, -1);
		int king_x=king_position/RANK;
		int king_y=king_position%FILE;
		board[king_x][king_y].selectPieceMovement(board, king_x, king_y);
		Vector<Integer> king_moves=board[king_x][king_y].getMoves();
		Vector<Integer> curr_moves=getCurrentMoves(board);
		printVector(curr_moves);
		//check king moves with our moves
		if(king_moves.size()==0 && curr_moves.size()==0)
		{
			System.out.println("stalemate");
			return true;
		}
		for(int i=0;i<king_moves.size();i++){
			if(!curr_moves.contains(king_moves.elementAt(i)))
				return false;
		}
		if(!curr_moves.contains(king_position))
			return false;
		//check if any other move by opponent can stop checkMate
		Vector<Integer> other_moves=getOtherMoves(board,king_x,king_y);
		for(int i=0;i<other_moves.size();i++){
			if(!checkOtherMove(board,other_moves.elementAt(i),king_moves,color)){
				return false;
			}
		}
		
		return true;
	}
	/***
	 * Then check if a other player other chess piece can stop check Mate
	 * @param board
	 * @param enemy
	 * @param kingMoves
	 * @param enemyColor
	 * @return true if opponent cannot stop our moves else false
	 */
	private boolean checkOtherMove(Piece[][] board, int enemy,Vector<Integer> kingMoves,
			int enemyColor){
		int enemyX=enemy/RANK;
		int enemyY=enemy%FILE;
		int originalColor=board[enemyX][enemyY].getColor();
		board[enemyX][enemyY].setColor(enemyColor);
		Vector<Integer> curr_next_moves=getCurrentMoves(board);
		for(int i=0;i<kingMoves.size();i++){
			if(!curr_next_moves.contains(kingMoves.elementAt(i))){
				board[enemyX][enemyY].setColor(originalColor);
				return false;
			}
		}
		board[enemyX][enemyY].setColor(originalColor);
		return true;
	}
	/***
	 * Given king current position kx and ky find other moves opponent can make
	 * @param board
	 * @param kx
	 * @param ky
	 * @return vector of moves of opponent
	 */
	private Vector<Integer> getOtherMoves(Piece[][] board, int kx,int ky) {
		Vector<Integer> all_moves=new Vector<Integer>();
		Vector<Integer> curr_moves=new Vector<Integer>();
		for(int rank=0;rank<RANK;rank++){
			for(int file=0;file<FILE;file++){
				if(board[rank][file].getColor()!=0 &&
						this.getColor()!=board[rank][file].getColor() &&
						rank!=kx && file!=ky){
					board[rank][file].selectPieceMovement(board, rank, file);
					curr_moves=board[rank][file].getMoves();
					for(int i=0;i<curr_moves.size();i++)
						all_moves.add(curr_moves.elementAt(i));
				}
			}
		}
		return all_moves;
	}
	/****
	 * Get all the moves current player can make
	 * @param board
	 * @return vector of moves
	 */
	private Vector<Integer> getCurrentMoves(Piece [][] board) {
		Vector<Integer> all_moves=new Vector<Integer>();
		Vector<Integer> curr_moves=new Vector<Integer>();
		for(int rank=0;rank<RANK;rank++){
			for(int file=0;file<FILE;file++){
				if(board[rank][file].getColor()!=0 &&
						this.getColor()==board[rank][file].getColor()){
					board[rank][file].selectPieceMovement(board, rank, file);
					curr_moves=board[rank][file].getMoves();
					for(int i=0;i<curr_moves.size();i++)
						all_moves.add(curr_moves.elementAt(i));
				}
			}
		}
		return all_moves;
	}
	/**
	 * Find the movement chess piece can make at given coordinates
	 * @param board
	 * @param currX
	 * @param currY
	 */
	public abstract void selectPieceMovement(Piece[][] board,int currX,int currY);
	/***
	 * Make movement to target from curr_x,curr_y
	 * @param board
	 * @param currX
	 * @param currY
	 * @param target
	 */
	protected abstract void makePieceMove(Piece[][] board,int currX,int currY,int target);
	/***
	 * Check if King is in check
	 * @param board
	 * @param currX
	 * @param currY
	 * @return true if king in check, else false
	 */
	protected abstract boolean callCheck(Piece[][] board,int currX,int currY);
}
