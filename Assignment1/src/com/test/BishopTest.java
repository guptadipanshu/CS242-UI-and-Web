package com.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.chess.game.Board;

public class BishopTest {
	private final int BLACK=-1;
	private final int WHITE=1;
	@Test
	public void test() {
		testBottomLeft();
		testBottomRight();
		testTopLeft();
		testTopRight();
		testCenterEnemy();
		testKillBottomLeft();
		testKillTwoPlayer();
	}

	private void testKillTwoPlayer() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Bishop",WHITE,7,0);
		board.addPlayer("Bishop",BLACK,2,5);
		board.addPlayer("Bishop",BLACK,1,4);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Bishop",7,0);
		board.makeMovement("Bishop", 7,0,21);
		moves=board.selectPlayerMovement("Bishop",2,5);
		board.makeMovement("Bishop", 2,5,12);
		moves=board.selectPlayerMovement("Bishop",1,4);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(3);
		solution.add(5);
		solution.add(19);
		solution.add(21);
		solution.add(26);
		solution.add(30);
		solution.add(33);
		solution.add(39);
		solution.add(40);
		
		Collections.sort(solution);
		assertEquals(solution,moves);
	}

	private void testKillBottomLeft() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Bishop",WHITE,7,0);
		board.addPlayer("Bishop",BLACK,1,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Bishop",7,0);
		board.makeMovement("Bishop", 7,0,14);
		moves=board.selectPlayerMovement("Bishop",1,6);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		solution.add(5);
		solution.add(7);
		solution.add(23);
		for(int i=21;i<=62;i+=7)
			solution.add(i);
		Collections.sort(solution);
		assertEquals(solution,moves);
		
	}

	private void testCenterEnemy() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Bishop",WHITE,3,3);
		board.addPlayer("Bishop",BLACK,1,1);
		board.addPlayer("Bishop",BLACK,1,5);
		board.addPlayer("Pawn",BLACK,4,2);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Bishop",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=9;i<=26;i+=9)
			solution.add(i);
		for(int i=36;i<=63;i+=9)
			solution.add(i);
		solution.add(13);
		solution.add(20);
		solution.add(34);
		Collections.sort(solution);
		assertEquals(solution,moves);
		
	}

	private void testTopRight() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Bishop",WHITE,0,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Bishop",0,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=14;i<=62;i+=7)
			solution.add(i);
		assertEquals(solution,moves);
		
	}

	private void testTopLeft() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Bishop",WHITE,0,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Bishop",0,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=9;i<=63;i+=9)
			solution.add(i);
		assertEquals(solution,moves);
	}

	private void testBottomRight() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Bishop",WHITE,7,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Bishop",7,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=0;i<=61;i+=9)
			solution.add(i);
		assertEquals(solution,moves);
		
	}

	private void testBottomLeft() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Bishop",WHITE,7,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Bishop",7,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=7;i<=49;i+=7)
			solution.add(i);
		assertEquals(solution,moves);
	}

}
