package com.chess.game;

import java.util.Vector;

public class King extends Piece {
	private final int PLAYER_ID=4;
	public King(int set_color) {
		// TODO Auto-generated constructor stub
		this.setPlayer(set_color,PLAYER_ID);
	}
	/**
	 *Given current coordinates finds the position where chess piece can move 
	 */
	@Override
	public void selectMovement(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		this.clear_moves();
		insert_horiz_vertical(board,curr_x, curr_y);
		insert_diagonal(board,curr_x, curr_y);
	}
	private void insert_horiz_vertical(Piece[][] board,int curr_x, int curr_y) {
		if(curr_x+1!=RANK && board[curr_x+1][curr_y].getColor()!=this.getColor() )
			this.insert_move((curr_x+1)*RANK+curr_y);
		if(curr_x-1>=0 && board[curr_x-1][curr_y].getColor()!=this.getColor())
			this.insert_move((curr_x-1)*RANK+curr_y);
		if(curr_y+1!=FILE && board[curr_x][curr_y+1].getColor()!=this.getColor())
			this.insert_move(curr_x*RANK+(curr_y+1));
		if(curr_y-1>=0 && board[curr_x][curr_y-1].getColor()!=this.getColor())
			this.insert_move(curr_x*RANK+(curr_y-1));
	}
	private void insert_diagonal(Piece[][] board,int curr_x, int curr_y) {
		if(curr_x+1==RANK && curr_y==0 && board[curr_x-1][curr_y+1].getColor()!=this.getColor())
			this.insert_move((curr_x-1)*RANK+curr_y+1);
		if(curr_x+1==RANK && curr_y+1==FILE && board[curr_x-1][curr_y-1].getColor()!=this.getColor())
			this.insert_move((curr_x-1)*RANK+curr_y-1);
		if(curr_x+1==RANK && curr_y!=0 && curr_y+1 !=FILE){
			if(board[curr_x-1][curr_y+1].getColor()!=this.getColor())
				this.insert_move((curr_x-1)*RANK+curr_y+1);
			if(board[curr_x-1][curr_y-1].getColor()!=this.getColor())
				this.insert_move((curr_x-1)*RANK+curr_y-1);
		}
		if(curr_x==0 && curr_y==0 && board[curr_x+1][curr_y+1].getColor()!=this.getColor())
			this.insert_move((curr_x+1)*RANK+curr_y+1);
		if(curr_x==0 && curr_y+1==FILE && board[curr_x+1][curr_y-1].getColor()!=this.getColor())
			this.insert_move((curr_x+1)*RANK+curr_y-1);
		if(curr_x==0 && curr_y+1!=FILE && curr_y!=0){
			if(board[curr_x+1][curr_y+1].getColor()!=this.getColor())
				this.insert_move((curr_x+1)*RANK+curr_y+1);
			if(board[curr_x+1][curr_y-1].getColor()!=this.getColor())
				this.insert_move((curr_x+1)*RANK+curr_y-1);
		}
		if(curr_x>0 && curr_x+1!=RANK && curr_y>0 && curr_y+1!=FILE){
			if(board[curr_x+1][curr_y+1].getColor()!=this.getColor())
				this.insert_move((curr_x+1)*RANK+curr_y+1);
			if(board[curr_x+1][curr_y-1].getColor()!=this.getColor())
				this.insert_move((curr_x+1)*RANK+curr_y-1);
			if(board[curr_x-1][curr_y+1].getColor()!=this.getColor())
				this.insert_move((curr_x-1)*RANK+curr_y+1);
			if(board[curr_x-1][curr_y-1].getColor()!=this.getColor())
				this.insert_move((curr_x-1)*RANK+curr_y-1);
		}
	}
	@Override
	public void makeMovement(Piece[][] board, int curr_x, int curr_y,int target) {
		if(this.check_move(target)){
			int target_x=target/RANK;
			int target_y=target%FILE;
			this.clear_moves();
			board[target_x][target_y].Kill_player();
			Piece swap=board[curr_x][curr_y];
			board[curr_x][curr_y]=board[target_x][target_y];
			board[target_x][target_y]=swap;	
		}

	}
	/***
	 * Return true if king is under check by the given player
	 */
	@Override
	protected boolean callCheck(Piece[][] board, int curr_x, int curr_y) {
		this.selectMovement(board, curr_x, curr_y);
		Vector<Integer> moves= this.get_moves();
		int king_position=this.getKingPosition(board, curr_x, curr_y);
		System.out.println("king"+king_position);
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
	
}
