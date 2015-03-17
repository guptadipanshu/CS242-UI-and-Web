package com.chess.game.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	private Color red=Color.RED;
	private final int BLACK=-1;
	private final int WHITE=1;
	private final int FILE=8;
	private final int RANK=8;
	public boolean status=false;
	public String winner="";
	private int playerTurn=0;
	private int pieceX,pieceY;
	private String pieceName;
	public JButton [][] squares=new JButton[9][8];
	private Board board;
	private JTextField player1;
	private JTextField player2;
	private JTextArea score;
	private JTextArea check;
	private String play1="",play2="";
	private ImageIcon pieceIcon;
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
	private ImageIcon startBtn =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/play.jpg");
	private ImageIcon restartBtn =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/giveup.jpg");
	private ImageIcon giveupBtn =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/stop.jpg");
	private ImageIcon undoBtn =new ImageIcon("/home/dipanshu/workspace/Assignment1/src/Image/white/undo.jpg");
	private ButtonHandler button;
	/***
	 * Constructor to initialize the GUI
	 */
	public ChessBoardGUI()
	{
		super("CHESS Assignment 1.1");
		contents=getContentPane();
		contents.setLayout(new GridLayout(9,8));
		button=new ButtonHandler();
		intializeGUI();
	}//end of constructor
	/***
	 * Helper Method of GUI initialization
	 */
	private void intializeGUI() {
		 //TextHandler handler = null;
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
		 player1 = new JTextField();
		 player1.setText("Player1");
		 this.add(player1);
		 //player1.addActionListener(handler);
		 player2 = new JTextField();
		 player2.setText("Player2");
		 this.add(player2);
		 
		 score = new JTextArea();
		 score.setText("score");
		 this.add(score);
		 
		 check = new JTextArea();
		 check.setText("CLICK");
		 this.add(check);
		 
		 squares[8][4]=new JButton();
		 squares[8][4].setIcon(startBtn);
		 contents.add(squares[8][4]);
		 squares[8][4].addActionListener(button);
		 
		 squares[8][5]=new JButton();
		 squares[8][5].setIcon(undoBtn);
		 contents.add(squares[8][5]);
		 squares[8][5].addActionListener(button);
		 squares[8][6]=new JButton();
		 squares[8][6].setIcon(giveupBtn);
		 contents.add(squares[8][6]);
		 squares[8][6].addActionListener(button);
		 squares[8][7]=new JButton();
		 squares[8][7].setIcon(restartBtn);
		 contents.add(squares[8][7]);
		 squares[8][7].addActionListener(button);
		 
		 
		 
		 setSize(500,500);
		 setResizable(false);
		 setVisible(true);
		 setLocationRelativeTo(null);
	}
	/*****
	 * Add all pieces to start the game
	 */
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
	/**
	 * Add a given chess piece to rank and file
	 * @param name  Name of the piece to add
	 * @param color Black or White
	 * @param rank	Row to Add
	 * @param file	Column to Add
	 */
	public void addPiece(String name,int color,int rank,int file)
	{
		board.addPiece(name,color,rank,file);
		createImage(name,color,rank,file);
	}
	/**
	 * Helper Method to addPiece
	 * @param name  Name of the piece to add
	 * @param color Black or White
	 * @param rank	Row to Add
	 * @param file	Column to Add
	 */
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
	/**
	 * Returns the name of player
	 * @return name of player1
	 */
	public String getPlayer1(){
		return play1;
	}
	/***
	 * Set the current score 
	 * @param currScore send by user
	 */
	public void setScore(String currScore){
		score.setText("score "+currScore);
	}
	/**
	 * Get name of player2
	 * @return the name of player2
	 */
	public String getPlayer2(){
		return play2;
	}
	/**
	 * Initialize the game with given turn
	 * @param turn -1 indicates Black start 1 indicates White
	 */
	public void setTurn(int turn){
		playerTurn=turn;
	}
	/**
	 * Tell if the game is still in progress
	 * @return true if game is running else return false
	 */
	public boolean getGameStatus(){
		return this.status;
	}
	/**
	 * Set the status of game
	 * @param arg 
	 */
	public void setGameStatus(boolean arg){
		 status=arg;
	}
	/**
	 * Returns the name of winner
	 * @return winner
	 */
	public String getWinner(){
		System.out.println("Restarting");
		return winner;
	}
	
	//button Class to implement actions on the GUI
	private class ButtonHandler  implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			Object source=e.getSource();
			/*When user CLick start Button*/
			if(source==squares[8][4]){
				play1=player1.getText().toString();
				play2=player2.getText().toString();
				player1.setText(player1.getText().toString());
				player2.setText(player2.getText().toString());
				check.setText("PLAY");
			}
			if(status==false)
				return;
			//Helper method that calculates chess piece and their movement
			makePieceMove(source);
			//When a player Forefiet the game
			if(source==squares[8][6]){
				if(playerTurn==1)
				{	status=false;
					winner="Player2";
				}else if(playerTurn==-1)
				{
					status=false;
					winner="Player2";
				}
			}
			//When player restart the game
			if(source==squares[8][7]){
				status=false;
				winner="";
			}
		}
		/***
		 * Decide palyer turn and move chess Piece on GUI
		 * @param source the button clicked
		 */
		private void makePieceMove(Object source) {
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if(source==squares[i][j] && playerTurn!=0 &&
						playerTurn==board.getPieceColor(i, j) && squares[i][j].getBackground()!=Color.RED )
					{
						setBoardColor();
						pieceName= board.getPieceName(i, j);
						Vector<Integer> moves= new Vector<Integer>();
						moves=board.selectPieceMovement(pieceName,i,j);
						printVector(moves);
						pieceX=i;
						pieceY=j;
						setSquareColor(moves);
						boolean result=board.callCheck(pieceName, i, j);
						if(result)
							check.setText("CHECK");
						if(board.getPieceColor(i, j)==1)
							result=board.TestCheckMate("WHITE", i, j);
						else if(board.getPieceColor(i, j)==-1)
							result=board.TestCheckMate("BLACK", i, j);
						if(result){
							check.setText("CHECKMATE");
							status=false;
							if(board.getPieceColor(i, j)==-1)
								winner="Player2";
							else
								winner="Player1";
						}
						pieceIcon=(ImageIcon) squares[i][j].getIcon();
						break;
					}
					if(squares[i][j].getBackground()==Color.RED && source==squares[i][j]
							&& playerTurn!=0){
						board.makeMovement(pieceName,pieceX,pieceY,(i)*RANK+j);
						setBoardColor();
						squares[pieceX][pieceY].setIcon(null);
						squares[i][j].setIcon(pieceIcon);
						if(playerTurn==1){
							playerTurn=-1;
						}else
							playerTurn=1;
					}
				}
			 }
		}// end of action performed
		/**
		 * Mark the cells in board as blue grey
		 */
		private void setBoardColor() {
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if((i+j)%2!=0)
						squares[i][j].setBackground(black);
					else
						squares[i][j].setBackground(white);
				}
			}
		}
		/*
		 * Display all possible moves in RED
		 */
		private void setSquareColor(Vector<Integer> move){
			for(int i=0;i<move.size();i++){
				int currX=move.elementAt(i)/RANK;
				int currY=move.elementAt(i)% FILE;
				//System.out.println("change to red "+move.elementAt(i)+currX+" "+currY);
				squares[currX][currY].setBackground(red);
			}
		}
		/**
		 * Used for Debugging to see the moves a piece can make
		 * @param print the moves of a piece
		 */
		private void printVector(Vector<Integer> print){
			for(int i=0;i<print.size();i++)
				System.out.println(" "+print.elementAt(i));
		}
	}//end of button class

	
	
}//end of GUI Class
	