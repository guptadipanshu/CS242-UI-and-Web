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
	public void test1() {
		testBottomLeft();
	}
	@Test
	public void test2() {
		testBottomRight();
	}
	@Test
	public void test3() {
		testTopLeft();
	}
	@Test
	public void test4() {
		testTopRight();
	}
	@Test
	public void test5() {
		testCenterEnemy();
	}
	@Test
	public void test6() {
		testKillBottomLeft();
	}
	@Test
	public void test7() {
		testKillTwoPlayer();
	}
	private void testKillTwoPlayer() {
		// TODO Auto-generated method stub
		Board board=new Board();
		board.addEmptySpaces();
		board.addPiece("Bishop",WHITE,7,0);
		board.addPiece("Bishop",BLACK,2,5);
		board.addPiece("Bishop",BLACK,1,4);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Bishop",7,0);
		board.makeMovement("Bishop", 7,0,21);
		moves=board.selectPieceMovement("Bishop",2,5);
		board.makeMovement("Bishop", 2,5,12);
		moves=board.selectPieceMovement("Bishop",1,4);
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
		board.addPiece("Bishop",WHITE,7,0);
		board.addPiece("Bishop",BLACK,1,6);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Bishop",7,0);
		board.makeMovement("Bishop", 7,0,14);
		moves=board.selectPieceMovement("Bishop",1,6);
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
		board.addPiece("Bishop",WHITE,3,3);
		board.addPiece("Bishop",BLACK,1,1);
		board.addPiece("Bishop",BLACK,1,5);
		board.addPiece("Pawn",BLACK,4,2);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Bishop",3,3);
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
		board.addPiece("Bishop",WHITE,0,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Bishop",0,7);
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
		board.addPiece("Bishop",WHITE,0,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Bishop",0,0);
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
		board.addPiece("Bishop",WHITE,7,7);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Bishop",7,7);
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
		board.addPiece("Bishop",WHITE,7,0);
		Vector<Integer> moves= new Vector<Integer>();
		moves=board.selectPieceMovement("Bishop",7,0);
		Collections.sort(moves);
		Vector<Integer> solution= new Vector<Integer>();
		for(int i=7;i<=49;i+=7)
			solution.add(i);
		assertEquals(solution,moves);
	}

}
