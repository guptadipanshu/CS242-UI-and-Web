package com.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.chess.game.Board;

public class RookTest {
	private final int BLACK=-1;
	private final int WHITE=1;
	@Test
	public void test() {
		
		testBootomLeft();
		testTopLeft();
		testBottomRight();
		testTopRight();
		testCenter();
		testPlayerBack();
		testOneKill();
		testTwoKill();
	}
	private void testTwoKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Rook",WHITE,0,0);
		board.addPlayer("Rook",BLACK,5,0);
		board.addPlayer("Rook",BLACK,5,5);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Rook",0,0);
		board.makeMovement("Rook",0,0,40);
		moves=board.selectPlayerMovement("Rook",5,0);
		board.makeMovement("Rook",5,0,45);
		moves=board.selectPlayerMovement("Rook",5,5);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=5;i<=37;i+=8)
			solution.add(i);
		for(int i=53;i<=61;i+=8)
			solution.add(i);
		for(int i=40;i<=44;i+=1)
			solution.add(i);
		solution.add(46);
		solution.add(47);
		Collections.sort(solution);
		assertEquals(solution,moves);
	}
	private void testOneKill() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Rook",WHITE,0,0);
		board.addPlayer("Rook",BLACK,5,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Rook",0,0);
		board.makeMovement("Rook", 0,0,40);
		moves=board.selectPlayerMovement("Rook",5,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=41;i<=47;i++)
			solution.add(i);
		for(int i=0;i<=32;i+=8)
			solution.add(i);
		for(int i=48;i<=56;i+=8)
			solution.add(i);
		
		Collections.sort(solution);
		assertEquals(solution,moves);

	}
	private void testPlayerBack() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Rook",WHITE,3,3);
		board.addPlayer("Pawn",BLACK,5,3);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Rook",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=3;i<=19;i+=8)
			solution.add(i);
		for(int i=24;i<=26;i++)
			solution.add(i);
		for(int i=28;i<=31;i++)
			solution.add(i);
		for(int i=35;i<=43;i+=8)
			solution.add(i);
			
		assertEquals(solution,moves);
		
	}
	private void testBootomLeft() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Rook",WHITE,7,0);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Rook",7,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=0;i<=48;i+=8)
			solution.add(i);
		for(int i=57;i<=63;i++)
			solution.add(i);
		assertEquals(solution,moves);
		}
	private void testTopLeft() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Rook",WHITE,0,0);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Rook",0,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=1;i<=7;i++)
			solution.add(i);
		for(int i=8;i<=58;i+=8)
			solution.add(i);
		assertEquals(solution,moves);
		}
	private void testBottomRight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Rook",WHITE,7,7);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Rook",7,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=7;i<=55;i+=8)
			solution.add(i);
		for(int i=56;i<=62;i++)
			solution.add(i);
		assertEquals(solution,moves);
		}
	private void testTopRight() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Rook",WHITE,0,7);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Rook",0,7);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=0;i<=6;i++)
			solution.add(i);
		for(int i=15;i<=63;i+=8)
			solution.add(i);
		assertEquals(solution,moves);
		}
	private void testCenter() {
		Board board=new Board();
		board.addEmptySpaces();
		board.addPlayer("Rook",WHITE,3,3);
		
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPlayerMovement("Rook",3,3);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=3;i<=19;i+=8)
			solution.add(i);
		for(int i=24;i<=26;i++)
			solution.add(i);
		for(int i=28;i<=31;i++)
			solution.add(i);
		for(int i=35;i<=59;i+=8)
			solution.add(i);
		assertEquals(solution,moves);
		}
}
