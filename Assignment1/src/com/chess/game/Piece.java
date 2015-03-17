package com.chess.game;

import java.util.Vector;

abstract public class Piece {
	private int color=0;
	private int player_id=0;
	private int alive=0;
	private int check_king=0;
	private Vector<Integer> valid_moves;
	protected final int RANK=8;
	protected final int FILE=8;
	/**
	 * Initialize the player
	 * @param set_color
	 * @param set_id is the player id
	 */
	public void setPlayer(int set_color,int set_id) {
		color=set_color;
		player_id=set_id;
		alive=1;
		check_king=0;
		valid_moves=new Vector<Integer>();
	}
	/**
	 * Mark a player dead
	 */
	protected void Kill_player(){
		alive=0;
		color=0;
	}
	/**
	 * Mark the king under check
	 */
	protected void check_king(){
		check_king=1;
	}
	/**
	 * Mark player color as -1 for black,0 for no player,1 for white 
	 * @param playerColor
	 */
	protected void setColor(int playerColor) {
		color=playerColor;
	}
	/**
	 * Mark King not under check
	 */
	protected void uncheck_king(){
		check_king=1;
	}
	/**
	 * Return true if player is alive
	 * @return
	 */
	public int PlayerStatus(){
		return this.alive;
	}
	/**
	 * Get player color
	 * @return -1 for black,1 for white else 0
	 */
	public int getColor(){
		return this.color;
	}
	/**
	 * @return player id
	 */
	public int getPlayer(){
		return this.player_id;
	}
	/**
	 * Return a string containing player name
	 * @return palyerName
	 */
	public String getPlayerName(){
		String name="";
		switch(player_id){
		case 0 : name="Empty";
				 break;
		case 1 : name= "Rook";
				 break;
		case 2:  name= "Knight";
				 break;
		case 3 : name="Bishop";
		 		 break;
		case 4 : name= "King";
				 break;
		case 5:  name= "Queen";
		 		 break; 
		case 9:  name="Pawn";
				 break;
		}
		return name;
	}
	/**
	 * Add a move as valid
	 * @param position
	 */
	protected void insert_move(int position){
		//System.out.println(position);
		valid_moves.add(position);
	}
	/**
	 * Mark all moves empty
	 */
	protected void clear_moves(){
		valid_moves.clear();
	}
	/**
	 * Get moves player can make
	 * @return vector containing moves
	 */
	protected Vector<Integer> get_moves(){
		return this.valid_moves;
	}
	/**
	 * Check if the given move is valid
	 * @param target
	 * @return true if valid move else false
	 */
	protected boolean check_move(int target){
	  return valid_moves.contains(target);
	}
	/**
	 * Print the vector containing moves
	 * @param print
	 */
	private void printVector(Vector<Integer> print){
		for(int i=0;i<print.size();i++)
			System.out.println(" "+print.elementAt(i));
	}
	/****
	 * Get the king position
	 * @param board
	 * @param curr_x
	 * @param curr_y
	 * @return king position
	 */
	protected int getKingPosition(Piece [][] board,int curr_x,int curr_y){
		for(int i=0;i<RANK;i++){
			for(int j=0;j<FILE;j++){
				if(i!=curr_x && j!=curr_y){
					if(board[i][j].getColor()!=0 &&
						this.getColor()!=board[i][j].getColor() &&
						board[i][j].getPlayerName().equals("King"))
								return i*RANK+j;
				}	
			}
		}
		return -1;
	}
	/***
	 * Check if King is in checkMate. First check if all moves
	 * made by other king are same as current player moves. Then check if a other
	 * player other chess piece can stop check Mate
	 * @param board
	 * @param color
	 * @return true if opponent is CheckMate else false
	 */
	protected boolean checkMate(Piece[][] board,int color){
		int king_position=this.getKingPosition(board, -1, -1);
		int king_x=king_position/RANK;
		int king_y=king_position%FILE;
		board[king_x][king_y].selectMovement(board, king_x, king_y);
		Vector<Integer> king_moves=board[king_x][king_y].get_moves();
		Vector<Integer> curr_moves=getCurrentMoves(board);
		printVector(curr_moves);
		//check king moves with our moves
		if(king_moves.size()==0 && curr_moves.size()==0)
			return true;
		for(int i=0;i<king_moves.size();i++){
			if(!curr_moves.contains(king_moves.elementAt(i)))
				return false;
		}
		//check if any other move by opponent can stop checkMate
		Vector<Integer> other_moves=getOtherMoves(board,king_x,king_y);
		for(int i=0;i<other_moves.size();i++){
			if(!checkOtherMove(board,other_moves.elementAt(i),king_moves,color)){
				return false;
			}
		}
		
		return true;
	}
	/***
	 * Then check if a other player other chess piece can stop check Mate
	 * @param board
	 * @param enemy
	 * @param king_moves
	 * @param enemyColor
	 * @return true if opponent cannot stop our moves else false
	 */
	private boolean checkOtherMove(Piece[][] board, int enemy,Vector<Integer> king_moves,
			int enemyColor){
		int enemy_x=enemy/RANK;
		int enemy_y=enemy%FILE;
		int original_color=board[enemy_x][enemy_y].getColor();
		board[enemy_x][enemy_y].setColor(enemyColor);
		Vector<Integer> curr_next_moves=getCurrentMoves(board);
		for(int i=0;i<king_moves.size();i++){
			if(!curr_next_moves.contains(king_moves.elementAt(i))){
				board[enemy_x][enemy_y].setColor(original_color);
				return false;
			}
		}
		board[enemy_x][enemy_y].setColor(original_color);
		return true;
	}
	/***
	 * Given king current position kx and ky find other moves opponent can make
	 * @param board
	 * @param kx
	 * @param ky
	 * @return vector of moves of opponent
	 */
	private Vector<Integer> getOtherMoves(Piece[][] board, int kx,int ky) {
		Vector<Integer> all_moves=new Vector<Integer>();
		Vector<Integer> curr_moves=new Vector<Integer>();
		for(int rank=0;rank<RANK;rank++){
			for(int file=0;file<FILE;file++){
				if(board[rank][file].getColor()!=0 &&
						this.getColor()!=board[rank][file].getColor() &&
						rank!=kx && file!=ky){
					board[rank][file].selectMovement(board, rank, file);
					curr_moves=board[rank][file].get_moves();
					for(int i=0;i<curr_moves.size();i++)
						all_moves.add(curr_moves.elementAt(i));
				}
			}
		}
		return all_moves;
	}
	/****
	 * Get all the moves current player can make
	 * @param board
	 * @return vector of moves
	 */
	private Vector<Integer> getCurrentMoves(Piece [][] board) {
		Vector<Integer> all_moves=new Vector<Integer>();
		Vector<Integer> curr_moves=new Vector<Integer>();
		for(int rank=0;rank<RANK;rank++){
			for(int file=0;file<FILE;file++){
				if(board[rank][file].getColor()!=0 &&
						this.getColor()==board[rank][file].getColor()){
					board[rank][file].selectMovement(board, rank, file);
					curr_moves=board[rank][file].get_moves();
					for(int i=0;i<curr_moves.size();i++)
						all_moves.add(curr_moves.elementAt(i));
				}
			}
		}
		return all_moves;
	}
	/**
	 * Find the movement player can make
	 * @param board
	 * @param curr_x
	 * @param curr_y
	 */
	protected abstract void selectMovement(Piece[][] board,int curr_x,int curr_y);
	/***
	 * Make movement to target from curr_x,curr_y
	 * @param board
	 * @param curr_x
	 * @param curr_y
	 * @param target
	 */
	protected abstract void makeMovement(Piece[][] board,int curr_x,int curr_y,int target);
	/***
	 * Check if King is in check
	 * @param board
	 * @param curr_x
	 * @param curr_y
	 * @return true if king in check, else false
	 */
	protected abstract boolean callCheck(Piece[][] board,int curr_x,int curr_y);
}
