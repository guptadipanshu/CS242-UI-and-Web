package com.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.chess.game.Board;

public class KingTest {
	private final int BLACK=-1;
	private final int WHITE=1;
	@Test
	public void test() {
		testBootomLeft();
		testBottomRight();
		testTopLeft();
		testTopRight();
		testCenter();
		testCenterInvalid();
		testOneKill();
		testTwoKill();
		
	}

	private void testTwoKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",BLACK,2,1);
		board.addPlayer("King",WHITE,0,1);
		board.addPlayer("Rook",BLACK,1,1);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",0,1);
		board.makeMovement("King", 0,1,9);
		moves=board.selectPlayerMovement("King",1,1);
		board.makeMovement("King", 1,1,17);
		moves=board.selectPlayerMovement("King",2,1);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(8);
		solution.add(9);
		solution.add(10);
		solution.add(16);
		solution.add(18);
		solution.add(24);
		solution.add(25);
		solution.add(26);
		Collections.sort(solution);
		assertEquals(solution,moves);
	}

	private void testOneKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",WHITE,0,0);
		board.addPlayer("King",WHITE,0,1);
		board.addPlayer("Rook",BLACK,1,1);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",0,1);
		board.makeMovement("King", 0,1,9);
		moves=board.selectPlayerMovement("King",1,1);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(1);
		solution.add(2);
		solution.add(8);
		solution.add(10);
		solution.add(16);
		solution.add(17);
		solution.add(18);
		Collections.sort(solution);
		assertEquals(solution,moves);	
		}

	private void testCenterInvalid() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",WHITE,3,3);
		board.addPlayer("Pawn",WHITE,2,3);
		board.addPlayer("Pawn",BLACK,4,3);
		board.addPlayer("Pawn",BLACK,4,2);
		board.addPlayer("Pawn",WHITE,2,2);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(20);
		solution.add(26);
		solution.add(28);
		solution.add(34);
		solution.add(35);
		solution.add(36);
		assertEquals(solution,moves);
		
	}

	private void testCenter() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",WHITE,3,3);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(18);
		solution.add(19);
		solution.add(20);
		solution.add(26);
		solution.add(28);
		solution.add(34);
		solution.add(35);
		solution.add(36);
		assertEquals(solution,moves);
	}

	private void testTopRight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",WHITE,0,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",0,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(6);
		solution.add(14);
		solution.add(15);
		assertEquals(solution,moves);
	}

	private void testTopLeft() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",WHITE,0,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",0,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(1);
		solution.add(8);
		solution.add(9);
		assertEquals(solution,moves);
	}

	private void testBottomRight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",WHITE,7,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",7,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(54);
		solution.add(55);
		solution.add(62);
		assertEquals(solution,moves);
	}

	private void testBootomLeft() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("King",WHITE,7,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("King",7,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(48);
		solution.add(49);
		solution.add(57);
		assertEquals(solution,moves);
		
	}

}
