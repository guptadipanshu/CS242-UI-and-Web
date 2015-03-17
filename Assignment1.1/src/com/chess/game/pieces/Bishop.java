package com.chess.game.pieces;

import java.util.Vector;

import com.chess.game.Piece;

public class Bishop extends Piece {
	private final int PIECE_ID=3;
	public Bishop(int setColor) {
		// TODO Auto-generated constructor stub
		this.setPiece(setColor,PIECE_ID);
	}
	/**
	 * Find the movement Bishop can make
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
		if(currX+1==RANK && currY+1==FILE)
			selectBottomRight(board, currX, currY);
		if(currX+1==RANK && currY!=0 && currY+1 !=FILE)
			selectBottomCenter(board, currX, currY);
		if(currX==0 && currY==0)
			selectTopLeft(board, currX, currY);
		if(currX==0 && currY+1==FILE)
			selectTopRight(board, currX, currY);
		if(currX==0 && currY!=0 && currY+1 !=FILE)
			selectTopCenter(board, currX, currY);
		if(currX>0 && currX+1!=RANK && currY>0 && currY+1!=FILE)
			selectCenter(board, currX, currY);
	}
	
	private void selectBottomRight(Piece[][] board, int currX, int currY) {
		for(int rank=currX-1,file=currY-1;rank>=0 && file>=0;rank--,file--){
			if(board[rank][file].getColor()!=this.getColor()){
				this.insertMove(rank*RANK+file);
				if(board[rank][file].getColor()!=0)
					break;
			}else
				break;
		}
	}//end bottom right
	
	private void selectBottomLeft(Piece[][] board, int currX, int currY) {
		for(int rank=currX-1,file=currY+1;rank>=0 && file<FILE;rank--,file++){
			if(board[rank][file].getColor()!=this.getColor()){
				this.insertMove(rank*RANK+file);
				if(board[rank][file].getColor()!=0)
					break;
			}
			else
				break;
		}
	}//end bottom left
	
	private void selectTopLeft(Piece[][] board, int currX, int currY) {
		for(int rank=currX+1,file=currY+1;rank<RANK && file<FILE;rank++,file++){
			if(board[rank][file].getColor()!=this.getColor()){
				this.insertMove(rank*RANK+file);
				if(board[rank][file].getColor()!=0)
					break;
			}
			else
				break;
		}
	}//end top left
	
	private void selectTopRight(Piece[][] board, int currX, int currY) {
		for(int rank=currX+1,file=currY-1;rank<RANK && file>=0;rank++,file--){
			//System.out.println("board"+board[rank][file].getColor()+"my"+this.getColor());
			if(board[rank][file].getColor()!=this.getColor())
			{
				this.insertMove(rank*RANK+file);
				if(board[rank][file].getColor()!=0)
					break;
			}
			else
				break;
		}
	}//end top right
	
	private void selectBottomCenter(Piece[][] board, int currX, int currY) {
		selectBottomLeft(board, currX, currY);
		selectBottomRight(board, currX, currY);
	}//move bottom_center
	
	private void selectTopCenter(Piece[][] board, int currX, int currY){
		selectTopRight(board, currX, currY);
		selectTopLeft(board, currX, currY);
	}//move top_center
	
	private void selectCenter(Piece[][] board, int currX, int currY) {
		//System.out.println("center");
		selectTopRight(board, currX, currY);
		selectTopLeft(board, currX, currY);
		selectBottomLeft(board, currX, currY);
		selectBottomRight(board, currX, currY);
	}//move center
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
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
	
}
