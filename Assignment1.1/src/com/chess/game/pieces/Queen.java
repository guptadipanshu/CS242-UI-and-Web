package com.chess.game.pieces;
import java.util.Vector;

import com.chess.game.Piece;

public class Queen extends Piece {
	private final int PIECE_ID=5;
	private Piece bishop,rook;
	public Queen(int setColor) {
		// TODO Auto-generated constructor stub
		this.setPiece(setColor,PIECE_ID);
		bishop=new Bishop(setColor);
		rook=new Rook(setColor);
	}
	/**
	 * Find the movement Queen can make
	 * @param board
	 * @param currX
	 * @param currY
	 */
	@Override
	public void selectPieceMovement(Piece[][] board, int currX, int currY) {
		// TODO Auto-generated method stub
		//System.out.println("cx"+curr_x+"cy"+curr_y);
		this.clearMoves();
		bishop.clearMoves();
		rook.clearMoves();
		bishop.selectPieceMovement(board, currX, currY);
		rook.selectPieceMovement(board, currX, currY);
		Vector<Integer> Bishop_moves= new Vector<Integer>();
		Bishop_moves=bishop.getMoves();
		if(Bishop_moves.size()>0){
			for(int i=0;i<Bishop_moves.size();i++)
				this.insertMove(Bishop_moves.elementAt(i));
		}
		Vector<Integer> Rook_moves= new Vector<Integer>();
		Rook_moves=rook.getMoves();
		if(Rook_moves.size()>0){
			for(int i=0;i<Rook_moves.size();i++)
				this.insertMove(Rook_moves.elementAt(i));
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
	public void makePieceMove(Piece[][] board, int currX, int currY,int target) {
		if(this.checkMove(target)){
			//System.out.println("Once");
			int target_x=target/RANK;
			int target_y=target%FILE;
			this.clearMoves();
			board[target_x][target_y].killPiece();
			Piece swap=board[currX][currY];
			board[currX][currY]=board[target_x][target_y];
			board[target_x][target_y]=swap;	
			bishop.clearMoves();
			rook.clearMoves();
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
	protected boolean callCheck(Piece[][] board, int currX, int currY) {
		this.selectPieceMovement(board, currX, currY);
		Vector<Integer> moves= this.getMoves();
		int king_position=this.getKingPosition(board, currX, currY);
		System.out.println("king"+king_position);
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
}
