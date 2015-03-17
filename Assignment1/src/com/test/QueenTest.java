package com.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.chess.game.Board;

public class QueenTest {
	private final int BLACK=-1;
	private final int WHITE=1;
	@Test
	public void test() {
		testBottomLeft();
		testBottomRight();
		testTopLeft();
		testTopRight();
		testCenterEenmy();
		testOneKill();
		System.out.println("two kil");
		testTwoKill();
	}

	private void testTwoKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Queen",WHITE,0,0);
		board.addPlayer("King",BLACK,6,6);
		board.addPlayer("Knight",BLACK,7,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Queen",0,0);
		board.makeMovement("Queen",0,0,54);
		moves=board.selectPlayerMovement("Queen",6,6);
		board.makeMovement("Queen",6,6,62);
		moves=board.selectPlayerMovement("Queen",7,6);
		Collections.sort(moves);	
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=6;i<=54;i+=8)
			solution.add(i);
		for(int i=56;i<=61;i+=1)
			solution.add(i);
		for(int i=8;i<=53;i+=9)
			solution.add(i);
		solution.add(55);
		solution.add(63);
		Collections.sort(solution);
		assertEquals(solution,moves);	
	}

	private void testOneKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Queen",WHITE,0,0);
		board.addPlayer("King",BLACK,6,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Queen",0,0);
		board.makeMovement("Queen",0,0,54);
		moves=board.selectPlayerMovement("Queen",6,6);
		Collections.sort(moves);	
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=6;i<=47;i+=8)
			solution.add(i);
		solution.add(62);
		for(int i=48;i<=53;i+=1)
			solution.add(i);
		for(int i=0;i<=45;i+=9)
			solution.add(i);
		solution.add(55);
		solution.add(63);
		solution.add(47);
		solution.add(61);
		Collections.sort(solution);
		assertEquals(solution,moves);	
	}

	private void testCenterEenmy() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Queen",WHITE,3,3);
		board.addPlayer("Pawn",BLACK,4,2);
		board.addPlayer("Bishop",BLACK,1,1);
		board.addPlayer("Bishop",BLACK,1,5);
		board.addPlayer("Pawn",BLACK,4,2);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Queen",3,3);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=3;i<=19;i+=8)
			solution.add(i);
		for(int i=24;i<=26;i++)
			solution.add(i);
		for(int i=28;i<=31;i++)
			solution.add(i);
		for(int i=35;i<=59;i+=8)
			solution.add(i);
		for(int i=9;i<=26;i+=9)
			solution.add(i);
		for(int i=36;i<=63;i+=9)
			solution.add(i);
		solution.add(13);
		solution.add(20);
		solution.add(34);
		Collections.sort(moves);
		Collections.sort(solution);
		assertEquals(solution,moves);
		
	}

	private void testTopRight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Queen",WHITE,0,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Queen",0,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=14;i<=62;i+=7)
			solution.add(i);
		for(int i=0;i<=6;i++)
			solution.add(i);
		for(int i=15;i<=63;i+=8)
			solution.add(i);
		Collections.sort(solution);
		assertEquals(solution,moves);
	}

	private void testTopLeft() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Queen",WHITE,0,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Queen",0,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=9;i<=63;i+=9)
			solution.add(i);
		for(int i=1;i<=7;i++)
			solution.add(i);
		for(int i=8;i<=58;i+=8)
			solution.add(i);
		Collections.sort(solution);
		assertEquals(solution,moves);
	}

	private void testBottomRight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Queen",WHITE,7,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Queen",7,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=0;i<=61;i+=9)
			solution.add(i);
		for(int i=7;i<=55;i+=8)
			solution.add(i);
		for(int i=56;i<=62;i++)
			solution.add(i);
		Collections.sort(solution);
		assertEquals(solution,moves);	
	}

	private void testBottomLeft() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Queen",WHITE,7,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Queen",7,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=7;i<=49;i+=7)
			solution.add(i);
		for(int i=0;i<=48;i+=8)
			solution.add(i);
		for(int i=57;i<=63;i++)
			solution.add(i);
		Collections.sort(solution);
		assertEquals(solution,moves);		
	}

}
