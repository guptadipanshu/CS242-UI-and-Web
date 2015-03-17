package com.chess.game.pieces;

import com.chess.game.Piece;

public class Empty extends Piece {
	private final int PIECE_ID=0;
	
	/**
	 * This function would not find any space due to Empty spaces
	 * @param board
	 * @param currX
	 * @param currY
	 */
	@Override
	public void selectPieceMovement(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		System.out.println("Empty");
		
	}
	public Empty(int set_color) {
		// TODO Auto-generated constructor stub
		this.setPiece(set_color,PIECE_ID);
	}
	@Override
	public void makePieceMove(Piece[][] board, int currX, int currY,int target) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected boolean callCheck(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
