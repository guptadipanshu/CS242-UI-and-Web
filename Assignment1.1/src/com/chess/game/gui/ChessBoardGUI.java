package com.chess.game.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.chess.game.Board;
import com.chess.game.pieces.Bishop;
import com.chess.game.pieces.Empty;
import com.chess.game.pieces.King;
import com.chess.game.pieces.Knight;
import com.chess.game.pieces.Pawn;
import com.chess.game.pieces.Queen;
import com.chess.game.pieces.Rook;

public class ChessBoardGUI extends JFrame 
{
	private Container contents;
	private Color black=Color.GRAY;
	private Color white=Color.CYAN;
	private final int BLACK=-1;
	private final int WHITE=1;
	private final int FILE=8;
	private JButton [][] squares=new JButton[8][8];
	private Board board;
	private ImageIcon blackKnight =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/black/knight.png");
	private ImageIcon blackKing =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/black/king.png");
	private ImageIcon blackRook =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/black/rook.png");
	private ImageIcon blackBishop=new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/black/bishop.png");
	private ImageIcon blackQueen =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/black/queen.png");
	private ImageIcon blackPawn =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/black/pawn.png");
	private ImageIcon whiteKnight =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/knight.png");
	private ImageIcon whiteKing =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/king.png");
	private ImageIcon whiteRook =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/rook.png");
	private ImageIcon whiteBishop=new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/bishop.png");
	private ImageIcon whiteQueen =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/queen.png");
	private ImageIcon whitePawn =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/pawn.png");
	public ChessBoardGUI()
	{
		 super("CHESS Assignment 1.1");
		 contents=getContentPane();
		 contents.setLayout(new GridLayout(8,8));
		 ButtonHandler button=new ButtonHandler();
		 board=new Board();
		 board.addEmptySpaces();
		 for(int i=0;i<8;i++)
		 {
			 for(int j=0;j<8;j++)
			 {
				 squares[i][j]=new JButton();
				 if((i+j)%2!=0)
				 {
					 squares[i][j].setBackground(black);
				 }
				 else
				 {
					 squares[i][j].setBackground(white);
				 }
				 contents.add(squares[i][j]);
				 squares[i][j].addActionListener(button);
			 }
		 }
		 setSize(500,500);
		 setResizable(false);
		 setVisible(true);
		 setLocationRelativeTo(null);
	}//end of constructor
	
	public void addAllPieces(){
		board.intialize();
		squares[0][0].setIcon(blackRook);
		squares[0][1].setIcon(blackKnight);
		squares[0][2].setIcon(blackBishop);
		squares[0][3].setIcon(blackKing);
		squares[0][4].setIcon(blackQueen);
		squares[0][5].setIcon(blackBishop);
		squares[0][6].setIcon(blackKnight);
		squares[0][7].setIcon(blackRook);
		for(int file=0;file<FILE;file++)
			squares[1][file].setIcon(blackPawn);
		
		squares[7][0].setIcon(whiteRook);
		squares[7][1].setIcon(whiteKnight);
		squares[7][2].setIcon(whiteBishop);
		squares[7][3].setIcon(whiteKing);
		squares[7][4].setIcon(whiteQueen);
		squares[7][5].setIcon(whiteBishop);
		squares[7][6].setIcon(whiteKnight);
		squares[7][7].setIcon(whiteRook);
		for(int file=0;file<FILE;file++)
			squares[6][file].setIcon(whitePawn);
		
	}
	public void addPiece(String name,int color,int rank,int file)
	{
		board.addPiece(name,color,rank,file);
		createImage(name,color,rank,file);
	}
	private void createImage(String name, int color,int rank,int file) {
		switch(name){
			case "Rook":	if(color==BLACK)
								squares[rank][file].setIcon(blackRook);
							else
								squares[rank][file].setIcon(whiteRook);	
							break;
			case "Knight":	if(color==BLACK)
								squares[rank][file].setIcon(blackKnight);
							else
								squares[rank][file].setIcon(whiteKnight);	
							break;
			case "Bishop":	if(color==BLACK)
								squares[rank][file].setIcon(blackBishop);
							else
								squares[rank][file].setIcon(whiteBishop);	
							break;
			case "King"  :	if(color==BLACK)
								squares[rank][file].setIcon(blackKing);
							else
								squares[rank][file].setIcon(whiteKing);	
							break;
			case "Queen" :	if(color==BLACK)
								squares[rank][file].setIcon(blackQueen);
							else
								squares[rank][file].setIcon(whiteQueen);	
							break;
			case "Pawn"  :	if(color==BLACK)
								squares[rank][file].setIcon(blackPawn);
							else
								squares[rank][file].setIcon(whitePawn);				
							break;
		}
	}
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Object source=e.getSource();
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if(source==squares[i][j])
						System.out.println("clicked"+i+" " +j);
				}
			 }
		}
	}
}
	