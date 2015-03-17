package com.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.chess.game.Board;

public class KnightTest {
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
		board.addPlayer("Knight",WHITE,7,0);
		board.addPlayer("Knight",BLACK,5,1);
		board.addPlayer("Knight",BLACK,7,2);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Knight",7,0);
		Collections.sort(moves);
		board.makeMovement("Knight", 7, 0, 41);
		moves=board.selectPlayerMovement("Knight",5,1);
		board.makeMovement("Knight", 5, 1, 58);
		moves=board.selectPlayerMovement("Knight",7,2);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(41);
		solution.add(43);
		solution.add(48);
		solution.add(52);
		Collections.sort(moves);
		Collections.sort(solution);
		assertEquals(solution,moves);
	}
	private void testOneKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Knight",WHITE,7,0);
		board.addPlayer("Knight",BLACK,5,1);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Knight",7,0);
		Collections.sort(moves);
		board.makeMovement("Knight", 7, 0, 41);
		moves=board.selectPlayerMovement("Knight",5,1);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(24);
		solution.add(26);
		solution.add(56);
		solution.add(58);
		solution.add(51);
		solution.add(35);
		Collections.sort(moves);
		Collections.sort(solution);
		assertEquals(solution,moves);
		
	}
	private void testCenterInvalid() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Knight",WHITE,3,3);
		board.addPlayer("Pawn",WHITE,1,2);
		board.addPlayer("Pawn",BLACK,1,4);
		board.addPlayer("Pawn",BLACK,4,1);
		board.addPlayer("Pawn",WHITE,2,5);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Knight",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(12);
		solution.add(17);
		solution.add(33);
		solution.add(37);
		solution.add(42);
		solution.add(44);
		assertEquals(solution,moves);
		
	}

	private void testCenter() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Knight",WHITE,3,3);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Knight",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(10);
		solution.add(12);
		solution.add(17);
		solution.add(21);
		solution.add(33);
		solution.add(37);
		solution.add(42);
		solution.add(44);
		assertEquals(solution,moves);
	}

	private void testTopRight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Knight",WHITE,0,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Knight",0,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(13);
		solution.add(22);
		assertEquals(solution,moves);
	}

	private void testTopLeft() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Knight",WHITE,0,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Knight",0,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(10);
		solution.add(17);
		assertEquals(solution,moves);
	}

	private void testBottomRight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Knight",WHITE,7,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Knight",7,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(46);
		solution.add(53);
		assertEquals(solution,moves);
	}

	private void testBootomLeft() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Knight",WHITE,7,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Knight",7,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(41);
		solution.add(50);
		assertEquals(solution,moves);
		
	}
}
