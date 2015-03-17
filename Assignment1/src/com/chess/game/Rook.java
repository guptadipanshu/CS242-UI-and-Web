package com.chess.game;

import java.util.Vector;

public class Rook extends Piece {
	private final int PLAYER_ID=1;
	
	public Rook(int set_color) {
		// TODO Auto-generated constructor stub
		this.setPlayer(set_color,PLAYER_ID);
	}
	/**
	 *Given current coordinates finds the position where chess piece can move 
	 */
	@Override
	public void selectMovement(Piece[][] board, int curr_x, int curr_y) {
		// Check for all positions in the file and rank i.e columns,rows
		this.clear_moves();
		insert_down(board,curr_x,curr_y);
		insert_up(board,curr_x,curr_y);
		insert_right(board, curr_x, curr_y);
		insert_left(board, curr_x, curr_y);
			
	}
	/**Given current coordinates finds valid moves on left **/
	private void insert_left(Piece[][] board, int curr_x, int curr_y) {
		for(int rank=curr_y-1;rank>=0;rank--){
			if(board[curr_x][rank].getPlayer()==0)
				this.insert_move(curr_x*RANK+rank);
			else if(board[curr_x][rank].getColor()!=this.getColor()){
				this.insert_move(curr_x*RANK+rank);
				break;
			}else
				break;
		}
	}
	/**Given current coordinates finds valid moves on right **/
	private void insert_right(Piece[][] board, int curr_x, int curr_y) {
		for(int rank=curr_y+1;rank<RANK;rank++){
			if(board[curr_x][rank].getPlayer()==0)
				this.insert_move(curr_x*RANK+rank);
			else if(board[curr_x][rank].getColor()!=this.getColor()){
				this.insert_move(curr_x*RANK+rank);
				break;
			}else
				break;
		}
	}
	/**Given current coordinates finds valid moves below **/
	private void insert_down(Piece[][] board, int curr_x, int curr_y){
		for(int file=curr_x+1;file<RANK;file++){
			if(board[file][curr_x].getPlayer()==0)
				this.insert_move(file*RANK+curr_y);
			else if(board[file][curr_x].getColor()!=this.getColor()){
				this.insert_move(file*RANK+curr_y);
				break;
			}else
				break;
		}
	}
	/**Given current coordinates finds valid moves above **/
	private void insert_up(Piece[][] board, int curr_x, int curr_y){
		for(int file=curr_x-1;file>=0;file--){
			if(board[file][curr_x].getPlayer()==0)
				this.insert_move(file*RANK+curr_y);
			else if(board[file][curr_x].getColor()!=this.getColor()){
				this.insert_move(file*RANK+curr_y);
				break;
			}else
				break;
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
