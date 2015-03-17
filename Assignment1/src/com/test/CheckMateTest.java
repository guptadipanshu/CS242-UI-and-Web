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
	public void test() {
		testBishop();
		testPawn();
		testQueen();
		testKnight();
		testCheckMate1();
		testCheckMate2();
		testCheckMate3();
	}

	private void testCheckMate3() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",BLACK,5,7);
		board.addPlayer("King",WHITE,3,7);
		board.addPlayer("Queen",WHITE,6,5);
		board.addPlayer("Rook",BLACK,6,0);
		boolean result=board.TestCheckMate("WHITE", 3, 7);
		assertEquals(false,result);
		
	}

	private void testCheckMate2() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",BLACK,5,7);
		board.addPlayer("King",WHITE,3,7);
		board.addPlayer("Queen",WHITE,6,5);
		board.addPlayer("Knight",BLACK,0,0);
		boolean result=board.TestCheckMate("WHITE", 3, 7);
		assertEquals(true,result);
	}

	private void testCheckMate1() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",BLACK,5,7);
		board.addPlayer("King",WHITE,3,7);
		board.addPlayer("Queen",WHITE,6,5);
		board.addPlayer("Knight",BLACK,4,0);
		boolean result=board.TestCheckMate("WHITE", 3, 7);
		assertEquals(true,result);
	}

	private void testKnight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Knight",WHITE,6,7);
		board.addPlayer("King",BLACK,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",7,6);
		board.makeMovement("King", 7, 6, 61);
		boolean result=board.callCheck("Knight", 6, 7);
		assertEquals(true,result);
		
	}

	private void testQueen() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Queen",WHITE,6,6);
		board.addPlayer("King",BLACK,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",7,6);
		board.makeMovement("King", 7, 6, 63);
		boolean result=board.callCheck("Queen", 6, 6);
		assertEquals(true,result);
	}

	private void testPawn() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",BLACK,6,6);
		board.addPlayer("King",WHITE,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",7,6);
		board.makeMovement("King", 7, 6, 63);
		boolean result=board.callCheck("Pawn", 6, 6);
		assertEquals(true,result);
	}

	private void testBishop() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Bishop",WHITE,0,0);
		board.addPlayer("King",BLACK,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",7,6);
		board.makeMovement("King", 7, 6, 63);
		boolean result=board.callCheck("Bishop", 0, 0);
		assertEquals(true,result);
	}

}
