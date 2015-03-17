package com.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.chess.game.Board;

public class CheckMateTest {
	private final int BLACK=-1;
	private final int WHITE=1;
	@Test
	public void test1() {
		testBishop();
	}
	@Test
	public void test2() {
		testPawn();
	}
	@Test
	public void test3() {
		testQueen();
	}
	@Test
	public void test4() {
		testKnight();
	}
	@Test
	public void test5() {
		testCheckMate1();
	}
	@Test
	public void test6() {
		testCheckMate2();
	}
	@Test
	public void test7() {
		testCheckMate3();
	}
	private void testCheckMate3() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("King",BLACK,5,7);
		board.addPiece("King",WHITE,3,7);
		board.addPiece("Queen",WHITE,6,5);
		board.addPiece("Rook",BLACK,6,0);
		boolean result=board.TestCheckMate("WHITE", 3, 7);
		assertEquals(false,result);
		
	}

	private void testCheckMate2() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("King",BLACK,5,7);
		board.addPiece("King",WHITE,3,7);
		board.addPiece("Queen",WHITE,6,5);
		board.addPiece("Knight",BLACK,0,0);
		boolean result=board.TestCheckMate("WHITE", 3, 7);
		assertEquals(true,result);
	}

	private void testCheckMate1() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("King",BLACK,5,7);
		board.addPiece("King",WHITE,3,7);
		board.addPiece("Queen",WHITE,6,5);
		board.addPiece("Knight",BLACK,4,0);
		boolean result=board.TestCheckMate("WHITE", 3, 7);
		assertEquals(true,result);
	}

	private void testKnight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Knight",WHITE,6,7);
		board.addPiece("King",BLACK,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("King",7,6);
		board.makeMovement("King", 7, 6, 61);
		boolean result=board.callCheck("Knight", 6, 7);
		assertEquals(true,result);
		
	}

	private void testQueen() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Queen",WHITE,6,6);
		board.addPiece("King",BLACK,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("King",7,6);
		board.makeMovement("King", 7, 6, 63);
		boolean result=board.callCheck("Queen", 6, 6);
		assertEquals(true,result);
	}

	private void testPawn() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Pawn",BLACK,6,6);
		board.addPiece("King",WHITE,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("King",7,6);
		board.makeMovement("King", 7, 6, 63);
		boolean result=board.callCheck("Pawn", 6, 6);
		assertEquals(true,result);
	}

	private void testBishop() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Bishop",WHITE,0,0);
		board.addPiece("King",BLACK,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("King",7,6);
		board.makeMovement("King", 7, 6, 63);
		boolean result=board.callCheck("Bishop", 0, 0);
		assertEquals(true,result);
	}

}
