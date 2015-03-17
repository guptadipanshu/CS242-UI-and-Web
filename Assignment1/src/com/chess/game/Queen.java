package com.chess.game;
import java.util.Vector;

public class Queen extends Piece {
	private final int PLAYER_ID=5;
	private Piece bishop,rook;
	public Queen(int set_color) {
		// TODO Auto-generated constructor stub
		this.setPlayer(set_color,PLAYER_ID);
		bishop=new Bishop(set_color);
		rook=new Rook(set_color);
	}
	/**
	 *Given current coordinates finds the position where chess piece can move 
	 */
	@Override
	public void selectMovement(Piece[][] board, int curr_x, int curr_y) {
		// TODO Auto-generated method stub
		//System.out.println("cx"+curr_x+"cy"+curr_y);
		this.clear_moves();
		bishop.clear_moves();
		rook.clear_moves();
		bishop.selectMovement(board, curr_x, curr_y);
		rook.selectMovement(board, curr_x, curr_y);
		Vector<Integer> Bishop_moves= new Vector<Integer>();
		Bishop_moves=bishop.get_moves();
		if(Bishop_moves.size()>0){
			for(int i=0;i<Bishop_moves.size();i++)
				this.insert_move(Bishop_moves.elementAt(i));
		}
		Vector<Integer> Rook_moves= new Vector<Integer>();
		Rook_moves=rook.get_moves();
		if(Rook_moves.size()>0){
			for(int i=0;i<Rook_moves.size();i++)
				this.insert_move(Rook_moves.elementAt(i));
		}
	}
	/**Given current coordinates move player to target**/
	@Override
	public void makeMovement(Piece[][] board, int curr_x, int curr_y,int target) {
		if(this.check_move(target)){
			//System.out.println("Once");
			int target_x=target/RANK;
			int target_y=target%FILE;
			this.clear_moves();
			board[target_x][target_y].Kill_player();
			Piece swap=board[curr_x][curr_y];
			board[curr_x][curr_y]=board[target_x][target_y];
			board[target_x][target_y]=swap;	
			bishop.clear_moves();
			rook.clear_moves();
		}
	}
	/**Given current coordinates returns true on CHECK at king**/
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
