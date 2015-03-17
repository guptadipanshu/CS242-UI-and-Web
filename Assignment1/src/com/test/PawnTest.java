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
	public void test() {
		testTop();
		testBottom();
		testEmptyFront();
		testNotEmptyFront();
		testDiagonalValid();
		testDiagonalInValid();
		testOneKill();
		testTwoKill();
	}

	private void testTwoKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",WHITE,6,0);
		board.addPlayer("Pawn",BLACK,5,1);
		board.addPlayer("Pawn",BLACK,4,2);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Pawn",6,0);
		board.makeMovement("Pawn", 6,0, 41);
		moves=board.selectPlayerMovement("Pawn",5,1);
		board.makeMovement("Pawn", 5,1, 34);
		moves=board.selectPlayerMovement("Pawn",4,2);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(26);
		Collections.sort(moves);
		assertEquals(solution,moves);
	}

	private void testOneKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",WHITE,6,0);
		board.addPlayer("Pawn",BLACK,5,1);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Pawn",6,0);
		board.makeMovement("Pawn", 6,0, 41);
		moves=board.selectPlayerMovement("Pawn",5,1);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(33);
		Collections.sort(moves);
		assertEquals(solution,moves);
	}

	private void testNotEmptyFront() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",WHITE,6,0);
		board.addPlayer("Pawn",WHITE,7,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Pawn",7,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		assertEquals(solution,moves);
	}

	private void testDiagonalValid() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",WHITE,3,3);
		board.addPlayer("Pawn",BLACK,2,2);
		board.addPlayer("Pawn",WHITE,2,4);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Pawn",3,3);
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
		board.addPlayer("Pawn",WHITE,3,3);
		board.addPlayer("Pawn",BLACK,2,2);
		board.addPlayer("Pawn",WHITE,2,4);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Pawn",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(18);
		solution.add(19);
		assertEquals(solution,moves);
		
	}

	private void testEmptyFront() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",WHITE,6,0);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Pawn",6,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(32);
		solution.add(40);
		assertEquals(solution,moves);
		
	}

	private void testTop() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",WHITE,0,0);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Pawn",0,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		assertEquals(solution,moves);
		}
	private void testBottom() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Pawn",BLACK,7,7);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Pawn",7,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		assertEquals(solution,moves);
		}
}
