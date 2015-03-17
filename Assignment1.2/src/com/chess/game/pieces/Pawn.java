package com.chess.game.pieces;

import java.util.Vector;

import com.chess.game.Piece;

public class Pawn extends Piece {
	private final int PLAYER_ID=9;
	public Pawn(int set_color) {
		// TODO Auto-generated constructor stubs
		this.setPiece(set_color,PLAYER_ID);
	}
	/**
	 * Find the movement Pawn can make
	 * @param board
	 * @param currX
	 * @param currY
	 */
	@Override
	public void selectPieceMovement(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		this.clearMoves();
		if(this.getColor()==-1)
			selectBlackPawn(board, curr_x,curr_y);
		else
			selectWhitePawn(board, curr_x,curr_y);
	}
	
	private void selectBlackPawn(Piece[][] board, int curr_x, int curr_y) {
		if(curr_x+1!=RANK){
			//move left
			if(curr_y!=0){
				int color=board[curr_x+1][curr_y-1].getColor();
				if(color!=this.getColor() && color!=0)
					this.insertMove((curr_x+1)*RANK+curr_y-1);	
			}
			//move right
			if(curr_y+1!=FILE){
				int color=board[curr_x+1][curr_y+1].getColor();
				if(color!=this.getColor() && color!=0)
					this.insertMove((curr_x+1)*RANK+curr_y+1);	
			}
			//move front
			if(board[curr_x+1][curr_y].getColor()==0)
				this.insertMove((curr_x+1)*RANK+curr_y);
			//move two front
			if(curr_x==1){
				int color_1=board[curr_x+1][curr_y].getColor();
				int color_2=board[curr_x+2][curr_y].getColor();
				if(color_1==0 && color_2==0)
					this.insertMove((curr_x+2)*RANK+curr_y);
			}
		}
		
	}
	private void selectWhitePawn(Piece[][] board, int curr_x, int curr_y) {

		if(curr_x!=0){
			//move left
			if(curr_y!=0){
				int color=board[curr_x-1][curr_y-1].getColor();
				if(color!=this.getColor() && color!=0)
					this.insertMove((curr_x-1)*RANK+curr_y-1);	
			}
			//move right
			if(curr_y+1!=FILE){
				int color=board[curr_x-1][curr_y+1].getColor();
				if(color!=this.getColor() && color!=0)
					this.insertMove((curr_x-1)*RANK+curr_y+1);	
			}
			//move front
			if(board[curr_x-1][curr_y].getColor()==0)
				this.insertMove((curr_x-1)*RANK+curr_y);
			//move two front
			if(curr_x==RANK-2){
				int color_1=board[curr_x-1][curr_y].getColor();
				int color_2=board[curr_x-2][curr_y].getColor();
				if(color_1==0 && color_2==0)
					this.insertMove((curr_x-2)*RANK+curr_y);
			}	
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
	public void makePieceMove(Piece[][] board, int curr_x, int curr_y,int target) {
		System.out.println("Trying piece called"+curr_x+" "+curr_y+ " "+ target );
		this.selectPieceMovement(board, curr_x, curr_y);
		if(this.checkMove(target)){
			System.out.println("MOVING piece called"+curr_x+" "+curr_y+ " " );
			int target_x=target/RANK;
			int target_y=target%FILE;
			this.clearMoves();
			board[target_x][target_y].killPiece();
			Piece swap=board[curr_x][curr_y];
			board[curr_x][curr_y]=board[target_x][target_y];
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
	protected boolean callCheck(Piece[][] board, int curr_x, int curr_y) {
		this.selectPieceMovement(board, curr_x, curr_y);
		Vector<Integer> moves= this.getMoves();
		int king_position=this.getKingPosition(board, curr_x, curr_y);
		//System.out.println("king"+king_position);
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
	

}
