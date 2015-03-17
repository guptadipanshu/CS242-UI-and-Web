package com.chess.game;

import java.util.Vector;

public class Knight extends Piece {
	private final int PLAYER_ID=2;
	public Knight(int set_color) {
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
		if(curr_x+1==RANK && curr_y==0)
			move_bottom_left(board, curr_x, curr_y);
		else if(curr_x+1==RANK && curr_y+1==FILE)
			move_bottom_right(board, curr_x, curr_y);
		else if(curr_x+1==RANK && curr_y!=0 && curr_y+1 !=FILE)
			move_bottom_center(board, curr_x, curr_y);
		else if(curr_x==0 && curr_y==0)
			move_top_left(board, curr_x, curr_y);
		else if(curr_x==0 && curr_y+1==FILE)
			move_top_right(board, curr_x, curr_y);
		else if(curr_x==0 && curr_y!=0 && curr_y+1 !=FILE)
			move_top_center(board, curr_x, curr_y);
		//if(curr_x>0 && curr_x+1!=RANK && curr_y>0 && curr_y+1!=FILE)
		else	
			move_center(board, curr_x, curr_y);
		
	}
	private void move_center(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		move_top_right(board, curr_x, curr_y);
		move_top_left(board, curr_x, curr_y);
		move_bottom_right(board, curr_x, curr_y);
		move_bottom_left(board, curr_x, curr_y);
		
	}
	private void move_top_center(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		move_top_right(board, curr_x, curr_y);
		move_top_left(board, curr_x, curr_y);
	}
	
	private void move_top_right(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		if(curr_x+2<RANK && curr_y-1>=0){
			if(board[curr_x+2][curr_y-1].getColor()!=this.getColor())
				this.insert_move((curr_x+2)*RANK+curr_y-1);
		}
		if(curr_x+1<RANK && curr_y-2>=0){
			if(board[curr_x+1][curr_y-2].getColor()!=this.getColor())
			this.insert_move((curr_x+1)*RANK+curr_y-2);
		}
	}
	
	private void move_top_left(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		if(curr_x+2<RANK && curr_y+1<FILE){
			if(board[curr_x+2][curr_y+1].getColor()!=this.getColor())
				this.insert_move((curr_x+2)*RANK+curr_y+1);
		}
		if(curr_x+1<RANK && curr_y+2<FILE){
			if(board[curr_x+1][curr_y+2].getColor()!=this.getColor())
			this.insert_move((curr_x+1)*RANK+curr_y+2);
		}
	}
	private void move_bottom_center(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		move_bottom_right(board, curr_x, curr_y);
		move_bottom_left(board, curr_x, curr_y);
		
	}
	
	private void move_bottom_right(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		if(curr_x-1>=0 && curr_y-2>=0){
			if(board[curr_x-1][curr_y-2].getColor()!=this.getColor())
				this.insert_move((curr_x-1)*RANK+curr_y-2);
		}
		if(curr_x-2>=0 && curr_y-1>=0){
			if(board[curr_x-2][curr_y-1].getColor()!=this.getColor())
			this.insert_move((curr_x-2)*RANK+curr_y-1);
		}
	}	
	private void move_bottom_left(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		if(curr_y+2<FILE && curr_x-1>=0){
			if(board[curr_x-1][curr_y+2].getColor()!=this.getColor())
				this.insert_move((curr_x-1)*RANK+curr_y+2);
		}
		if(curr_x-2>=0 && curr_y+1<FILE){
			if(board[curr_x-2][curr_y+1].getColor()!=this.getColor())
				this.insert_move((curr_x-2)*RANK+curr_y+1);
		}
	}
	/**Given current coordinates move player to target**/
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
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
	
}
