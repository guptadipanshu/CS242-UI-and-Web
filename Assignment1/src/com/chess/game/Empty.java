package com.chess.game;

public class Empty extends Piece {
	private final int PLAYER_ID=0;
	
	/**
	 *Given current coordinates finds the position where chess piece can move 
	 */
	@Override
	public void selectMovement(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		System.out.println("Empty");
		
	}
	public Empty(int set_color) {
		// TODO Auto-generated constructor stub
		this.setPlayer(set_color,PLAYER_ID);
	}
	@Override
	public void makeMovement(Piece[][] board, int curr_x, int curr_y,int target) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected boolean callCheck(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
