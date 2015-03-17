package com.chess.game;

import java.util.Vector;

public class Pawn extends Piece {
	private final int PLAYER_ID=9;
	public Pawn(int set_color) {
		// TODO Auto-generated constructor stubs
		this.setPlayer(set_color,PLAYER_ID);
	}
	/**
	 *Given current coordinates finds the position where chess piece can move 
	 */
	@Override
	public void selectMovement(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		this.clear_moves();
		if(this.getColor()==-1)
			move_black_pawn(board, curr_x,curr_y);
		else
			move_white_pawn(board, curr_x,curr_y);
	}
	
	private void move_black_pawn(Piece[][] board, int curr_x, int curr_y) {
		if(curr_x+1!=RANK){
			//move left
			if(curr_y!=0){
				int color=board[curr_x+1][curr_y-1].getColor();
				if(color!=this.getColor() && color!=0)
					this.insert_move((curr_x+1)*RANK+curr_y-1);	
			}
			//move right
			if(curr_y+1!=FILE){
				int color=board[curr_x+1][curr_y+1].getColor();
				if(color!=this.getColor() && color!=0)
					this.insert_move((curr_x+1)*RANK+curr_y+1);	
			}
			//move front
			if(board[curr_x+1][curr_y].getColor()==0)
				this.insert_move((curr_x+1)*RANK+curr_y);
			//move two front
			if(curr_x==1){
				int color_1=board[curr_x+1][curr_y].getColor();
				int color_2=board[curr_x+2][curr_y].getColor();
				if(color_1==0 && color_2==0)
					this.insert_move((curr_x+2)*RANK+curr_y);
			}
		}
		
	}
	private void move_white_pawn(Piece[][] board, int curr_x, int curr_y) {

		if(curr_x!=0){
			//move left
			if(curr_y!=0){
				int color=board[curr_x-1][curr_y-1].getColor();
				if(color!=this.getColor() && color!=0)
					this.insert_move((curr_x-1)*RANK+curr_y-1);	
			}
			//move right
			if(curr_y+1!=FILE){
				int color=board[curr_x-1][curr_y+1].getColor();
				if(color!=this.getColor() && color!=0)
					this.insert_move((curr_x-1)*RANK+curr_y+1);	
			}
			//move front
			if(board[curr_x-1][curr_y].getColor()==0)
				this.insert_move((curr_x-1)*RANK+curr_y);
			//move two front
			if(curr_x==RANK-2){
				int color_1=board[curr_x-1][curr_y].getColor();
				int color_2=board[curr_x-2][curr_y].getColor();
				if(color_1==0 && color_2==0)
					this.insert_move((curr_x-2)*RANK+curr_y);
			}	
		}
	}
	/**Given current coordinates move player to target**/
	@Override
	public void makeMovement(Piece[][] board, int curr_x, int curr_y,int target) {
		System.out.println(curr_x+" "+curr_y+ " " );
		if(this.check_move(target)){
			int target_x=target/RANK;
			int target_y=target%FILE;
			this.clear_moves();
			board[target_x][target_y].Kill_player();
			Piece swap=board[curr_x][curr_y];
			board[curr_x][curr_y]=board[target_x][target_y];
			board[target_x][target_y]=swap;
			System.out.println(curr_x+curr_y+ " " +target_x+target_y);
		}
	}
	/**Given current coordinates returns true on CHECK at king**/
	@Override
	protected boolean callCheck(Piece[][] board, int curr_x, int curr_y) {
		this.selectMovement(board, curr_x, curr_y);
		Vector<Integer> moves= this.get_moves();
		int king_position=this.getKingPosition(board, curr_x, curr_y);
		//System.out.println("king"+king_position);
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
	

}
