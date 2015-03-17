package com.chess.game;

import java.util.Vector;

public class Bishop extends Piece {
	private final int PLAYER_ID=3;
	public Bishop(int set_color) {
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
		if(curr_x+1==RANK && curr_y+1==FILE)
			move_bottom_right(board, curr_x, curr_y);
		if(curr_x+1==RANK && curr_y!=0 && curr_y+1 !=FILE)
			move_bottom_center(board, curr_x, curr_y);
		if(curr_x==0 && curr_y==0)
			move_top_left(board, curr_x, curr_y);
		if(curr_x==0 && curr_y+1==FILE)
			move_top_right(board, curr_x, curr_y);
		if(curr_x==0 && curr_y!=0 && curr_y+1 !=FILE)
			move_top_center(board, curr_x, curr_y);
		if(curr_x>0 && curr_x+1!=RANK && curr_y>0 && curr_y+1!=FILE)
			move_center(board, curr_x, curr_y);
	}
	
	private void move_bottom_right(Piece[][] board, int curr_x, int curr_y) {
		for(int rank=curr_x-1,file=curr_y-1;rank>=0 && file>=0;rank--,file--){
			if(board[rank][file].getColor()!=this.getColor()){
				this.insert_move(rank*RANK+file);
				if(board[rank][file].getColor()!=0)
					break;
			}else
				break;
		}
	}//end bottom right
	
	private void move_bottom_left(Piece[][] board, int curr_x, int curr_y) {
		for(int rank=curr_x-1,file=curr_y+1;rank>=0 && file<FILE;rank--,file++){
			if(board[rank][file].getColor()!=this.getColor()){
				this.insert_move(rank*RANK+file);
				if(board[rank][file].getColor()!=0)
					break;
			}
			else
				break;
		}
	}//end bottom left
	
	private void move_top_left(Piece[][] board, int curr_x, int curr_y) {
		for(int rank=curr_x+1,file=curr_y+1;rank<RANK && file<FILE;rank++,file++){
			if(board[rank][file].getColor()!=this.getColor()){
				this.insert_move(rank*RANK+file);
				if(board[rank][file].getColor()!=0)
					break;
			}
			else
				break;
		}
	}//end top left
	
	private void move_top_right(Piece[][] board, int curr_x, int curr_y) {
		for(int rank=curr_x+1,file=curr_y-1;rank<RANK && file>=0;rank++,file--){
			//System.out.println("board"+board[rank][file].getColor()+"my"+this.getColor());
			if(board[rank][file].getColor()!=this.getColor())
			{
				this.insert_move(rank*RANK+file);
				if(board[rank][file].getColor()!=0)
					break;
			}
			else
				break;
		}
	}//end top right
	
	private void move_bottom_center(Piece[][] board, int curr_x, int curr_y) {
		move_bottom_left(board, curr_x, curr_y);
		move_bottom_right(board, curr_x, curr_y);
	}//move bottom_center
	
	private void move_top_center(Piece[][] board, int curr_x, int curr_y){
		move_top_right(board, curr_x, curr_y);
		move_top_left(board, curr_x, curr_y);
	}//move top_center
	
	private void move_center(Piece[][] board, int curr_x, int curr_y) {
		//System.out.println("center");
		move_top_right(board, curr_x, curr_y);
		move_top_left(board, curr_x, curr_y);
		move_bottom_left(board, curr_x, curr_y);
		move_bottom_right(board, curr_x, curr_y);
	}//move center
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
