package com.chess.game.pieces;

import java.util.Vector;

import com.chess.game.Piece;

public class Knight extends Piece {
	private final int PIECE_ID=2;
	public Knight(int setColor) {
		// TODO Auto-generated constructor stub
		this.setPiece(setColor,PIECE_ID);
	}
	/**
	 * Find the movement Knight can make
	 * @param board
	 * @param currX
	 * @param currY
	 */
	@Override
	public void selectPieceMovement(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		this.clearMoves();
		if(currX+1==RANK && currY==0)
			selectBottomLeft(board, currX, currY);
		else if(currX+1==RANK && currY+1==FILE)
			selectBottomRight(board, currX, currY);
		else if(currX+1==RANK && currY!=0 && currY+1 !=FILE)
			selectBottomCenter(board, currX, currY);
		else if(currX==0 && currY==0)
			selectTopLeft(board, currX, currY);
		else if(currX==0 && currY+1==FILE)
			selectTopRight(board, currX, currY);
		else if(currX==0 && currY!=0 && currY+1 !=FILE)
			selectTopCenter(board, currX, currY);
		//if(curr_x>0 && curr_x+1!=RANK && curr_y>0 && curr_y+1!=FILE)
		else	
			selectCenter(board, currX, currY);
		
	}
	private void selectCenter(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		selectTopRight(board, currX, currY);
		selectTopLeft(board, currX, currY);
		selectBottomRight(board, currX, currY);
		selectBottomLeft(board, currX, currY);
		
	}
	private void selectTopCenter(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		selectTopRight(board, currX, currY);
		selectTopLeft(board, currX, currY);
	}
	
	private void selectTopRight(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		if(currX+2<RANK && currY-1>=0){
			if(board[currX+2][currY-1].getColor()!=this.getColor())
				this.insertMove((currX+2)*RANK+currY-1);
		}
		if(currX+1<RANK && currY-2>=0){
			if(board[currX+1][currY-2].getColor()!=this.getColor())
			this.insertMove((currX+1)*RANK+currY-2);
		}
	}
	
	private void selectTopLeft(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		if(currX+2<RANK && currY+1<FILE){
			if(board[currX+2][currY+1].getColor()!=this.getColor())
				this.insertMove((currX+2)*RANK+currY+1);
		}
		if(currX+1<RANK && currY+2<FILE){
			if(board[currX+1][currY+2].getColor()!=this.getColor())
			this.insertMove((currX+1)*RANK+currY+2);
		}
	}
	private void selectBottomCenter(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		selectBottomRight(board, currX, currY);
		selectBottomLeft(board, currX, currY);
		
	}
	
	private void selectBottomRight(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		if(currX-1>=0 && currY-2>=0){
			if(board[currX-1][currY-2].getColor()!=this.getColor())
				this.insertMove((currX-1)*RANK+currY-2);
		}
		if(currX-2>=0 && currY-1>=0){
			if(board[currX-2][currY-1].getColor()!=this.getColor())
			this.insertMove((currX-2)*RANK+currY-1);
		}
	}	
	private void selectBottomLeft(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		if(currY+2<FILE && currX-1>=0){
			if(board[currX-1][currY+2].getColor()!=this.getColor())
				this.insertMove((currX-1)*RANK+currY+2);
		}
		if(currX-2>=0 && currY+1<FILE){
			if(board[currX-2][currY+1].getColor()!=this.getColor())
				this.insertMove((currX-2)*RANK+currY+1);
		}
	}
	/***
	 * Make movement to target index from currX,currY
	 * @param board
	 * @param currX 
	 * @param currY
	 * @param target
	 */	@Override
	public void makePieceMove(Piece[][] board, int currX, int currY,int target) {
		if(this.checkMove(target)){
			int target_x=target/RANK;
			int target_y=target%FILE;
			this.clearMoves();
			board[target_x][target_y].killPiece();
			Piece swap=board[currX][currY];
			board[currX][currY]=board[target_x][target_y];
			board[target_x][target_y]=swap;
		}
	}
	/***
	 * Check if King is in check
	 * @param board
	 * @param currX
	 * @param currY
	 * @return true if king in check, else false
	 */
	@Override
	protected boolean callCheck(Piece[][] board, int currX, int currY) {
		this.selectPieceMovement(board, currX, currY);
		Vector<Integer> moves= this.getMoves();
		int king_position=this.getKingPosition(board, currX, currY);
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
	
}
