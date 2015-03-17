package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chess.game.gui.ChessBoardGUI;

public class GUITest {

	@Test
	public void test() {
		testEmptyBoard();
		while(true){
			
		}
	}

	private void testEmptyBoard() {
		ChessBoardGUI board=new ChessBoardGUI();
		board.addAllPieces();
	}

}
