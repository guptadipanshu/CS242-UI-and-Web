package com.chess.game.pieces;

import java.util.Vector;

import com.chess.game.Piece;

public class King extends Piece {
	private final int PIECE_ID=4;
	public King(int setColor) {
		// TODO Auto-generated constructor stub
		this.setPiece(setColor,PIECE_ID);
	}
	/**
	 * Find the movement KIng can make
	 * @param board
	 * @param currX
	 * @param currY
	 */
	@Override
	public void selectPieceMovement(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		this.clearMoves();
		selectHorizVertical(board,currX, currY);
		selectDiagonal(board,currX, currY);
	}
	private void selectHorizVertical(Piece[][] board,int currX, int currY) {
		if(currX+1!=RANK && board[currX+1][currY].getColor()!=this.getColor() )
			this.insertMove((currX+1)*RANK+currY);
		if(currX-1>=0 && board[currX-1][currY].getColor()!=this.getColor())
			this.insertMove((currX-1)*RANK+currY);
		if(currY+1!=FILE && board[currX][currY+1].getColor()!=this.getColor())
			this.insertMove(currX*RANK+(currY+1));
		if(currY-1>=0 && board[currX][currY-1].getColor()!=this.getColor())
			this.insertMove(currX*RANK+(currY-1));
	}
	private void selectDiagonal(Piece[][] board,int currX, int currY) {
		if(currX+1==RANK && currY==0 && board[currX-1][currY+1].getColor()!=this.getColor())
			this.insertMove((currX-1)*RANK+currY+1);
		if(currX+1==RANK && currY+1==FILE && board[currX-1][currY-1].getColor()!=this.getColor())
			this.insertMove((currX-1)*RANK+currY-1);
		if(currX+1==RANK && currY!=0 && currY+1 !=FILE){
			if(board[currX-1][currY+1].getColor()!=this.getColor())
				this.insertMove((currX-1)*RANK+currY+1);
			if(board[currX-1][currY-1].getColor()!=this.getColor())
				this.insertMove((currX-1)*RANK+currY-1);
		}
		if(currX==0 && currY==0 && board[currX+1][currY+1].getColor()!=this.getColor())
			this.insertMove((currX+1)*RANK+currY+1);
		if(currX==0 && currY+1==FILE && board[currX+1][currY-1].getColor()!=this.getColor())
			this.insertMove((currX+1)*RANK+currY-1);
		if(currX==0 && currY+1!=FILE && currY!=0){
			if(board[currX+1][currY+1].getColor()!=this.getColor())
				this.insertMove((currX+1)*RANK+currY+1);
			if(board[currX+1][currY-1].getColor()!=this.getColor())
				this.insertMove((currX+1)*RANK+currY-1);
		}
		if(currX>0 && currX+1!=RANK && currY>0 && currY+1!=FILE){
			if(board[currX+1][currY+1].getColor()!=this.getColor())
				this.insertMove((currX+1)*RANK+currY+1);
			if(board[currX+1][currY-1].getColor()!=this.getColor())
				this.insertMove((currX+1)*RANK+currY-1);
			if(board[currX-1][currY+1].getColor()!=this.getColor())
				this.insertMove((currX-1)*RANK+currY+1);
			if(board[currX-1][currY-1].getColor()!=this.getColor())
				this.insertMove((currX-1)*RANK+currY-1);
		}
	}
	/***
	 * Make movement to target index from currX,currY
	 * @param board
	 * @param currX 
	 * @param currY
	 * @param target
	 */
	@Override
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
		System.out.println("king"+king_position);
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
	
}
