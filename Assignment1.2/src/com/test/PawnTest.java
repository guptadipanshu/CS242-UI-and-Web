package com.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.chess.game.Board;

public class PawnTest {
	private final int BLACK=-1;
	private final int WHITE=1;
	@Test
	public void test1() {
		testTwoKill();
	}
	@Test
	public void test2() {
		testEmptyFront();
	}
	@Test
	public void test3() {
		testNotEmptyFront();
	}
	@Test
	public void test4() {
		testDiagonalValid();
	}
	@Test
	public void test5() {
		testDiagonalInValid();
	}
	@Test
	public void test6() {
		testTop();
	}
	@Test
	public void test7() {
		testOneKill();
	}
	@Test
	public void test8() {
		testBottom();
	}

	private void testTwoKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",WHITE,6,0);
		board.addPiece("Pawn",BLACK,5,1);
		board.addPiece("Pawn",BLACK,4,2);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Pawn",6,0);
		board.makeMovement("Pawn", 6,0, 41);
		moves=board.selectPieceMovement("Pawn",5,1);
		board.makeMovement("Pawn", 5,1, 34);
		moves=board.selectPieceMovement("Pawn",4,2);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(26);
		Collections.sort(moves);
		assertEquals(solution,moves);
	}

	private void testOneKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",WHITE,6,0);
		board.addPiece("Pawn",BLACK,5,1);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Pawn",6,0);
		//board.makeMovement("Pawn", 6,0, 41);
		//moves=board.selectPieceMovement("Pawn",5,1);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(32);
		solution.add(40);
		solution.add(41);
		Collections.sort(moves);
		assertEquals(solution,moves);
	}

	private void testNotEmptyFront() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",WHITE,6,0);
		board.addPiece("Pawn",WHITE,7,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Pawn",7,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		assertEquals(solution,moves);
	}

	private void testDiagonalValid() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",WHITE,3,3);
		board.addPiece("Pawn",BLACK,2,2);
		board.addPiece("Pawn",WHITE,2,4);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Pawn",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(18);
		solution.add(19);
		//solution.add(20);
		assertEquals(solution,moves);
		
	}
	private void testDiagonalInValid() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",WHITE,3,3);
		board.addPiece("Pawn",BLACK,2,2);
		board.addPiece("Pawn",WHITE,2,4);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Pawn",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(18);
		solution.add(19);
		assertEquals(solution,moves);
		
	}

	private void testEmptyFront() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",WHITE,6,0);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Pawn",6,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(32);
		solution.add(40);
		assertEquals(solution,moves);
		
	}

	private void testTop() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",WHITE,0,0);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Pawn",0,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		assertEquals(solution,moves);
		}
	private void testBottom() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",BLACK,7,7);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Pawn",7,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		assertEquals(solution,moves);
		}
}
