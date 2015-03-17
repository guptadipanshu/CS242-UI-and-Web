package com.chess.game.pieces;

import java.util.Vector;

import com.chess.game.Piece;

public class Rook extends Piece {
	private final int PIECE_ID=1;
	
	public Rook(int setColor) {
		// TODO Auto-generated constructor stub
		this.setPiece(setColor,PIECE_ID);
	}
	/**
	 * Find the movement Rook can make
	 * @param board
	 * @param currX
	 * @param currY
	 */
	@Override
	public void selectPieceMovement(Piece[][] board, int currX, int currY) {
		// Check for all positions in the file and rank i.e columns,rows
		this.clearMoves();
		selectDown(board,currX,currY);
		selectUp(board,currX,currY);
		selectRight(board, currX, currY);
		selectLeft(board, currX, currY);
			
	}
	/**Given current coordinates finds valid moves on left **/
	private void selectLeft(Piece[][] board, int currX, int currY) {
		for(int rank=currY-1;rank>=0;rank--){
			if(board[currX][rank].getPiece()==0)
				this.insertMove(currX*RANK+rank);
			else if(board[currX][rank].getColor()!=this.getColor()){
				this.insertMove(currX*RANK+rank);
				break;
			}else
				break;
		}
	}
	/**Given current coordinates finds valid moves on right **/
	private void selectRight(Piece[][] board, int currX, int currY) {
		for(int rank=currY+1;rank<RANK;rank++){
			if(board[currX][rank].getPiece()==0)
				this.insertMove(currX*RANK+rank);
			else if(board[currX][rank].getColor()!=this.getColor()){
				this.insertMove(currX*RANK+rank);
				break;
			}else
				break;
		}
	}
	/**Given current coordinates finds valid moves below **/
	private void selectDown(Piece[][] board, int currX, int currY){
		for(int file=currX+1;file<RANK;file++){
			if(board[file][currX].getPiece()==0)
				this.insertMove(file*RANK+currY);
			else if(board[file][currX].getColor()!=this.getColor()){
				this.insertMove(file*RANK+currY);
				break;
			}else
				break;
		}
	}
	/**Given current coordinates finds valid moves above **/
	private void selectUp(Piece[][] board, int currX, int currY){
		for(int file=currX-1;file>=0;file--){
			if(board[file][currX].getPiece()==0)
				this.insertMove(file*RANK+currY);
			else if(board[file][currX].getColor()!=this.getColor()){
				this.insertMove(file*RANK+currY);
				break;
			}else
				break;
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
			int target_x=target/RANK;
			int target_y=target%FILE;
			this.clearMoves();
			board[target_x][target_y].killPiece();
			Piece swap=board[currX][currY];
			board[currX][currY]=board[target_x][target_y];
			board[target_x][target_y]=swap;
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
		//System.out.println("king"+king_position);
		if(moves.contains(king_position))
			return true;
		else
			return false;
	}
	
}
