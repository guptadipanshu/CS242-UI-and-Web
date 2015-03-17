package gameLoop;

import java.util.HashMap;
import java.util.Scanner;

import com.chess.game.gui.ChessBoardGUI;

public class GameLoop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String player1="";
		String player2="";
		ChessBoardGUI board=new ChessBoardGUI();
		board.addAllPieces();
		boolean start=false;
		HashMap score = new HashMap();
		startGame(board, score,player1,player2);
		while(true){
			start=board.getGameStatus();
			if(!start){
				System.out.println("Restarting");
				player1=board.getPlayer1();
				player2=board.getPlayer2();
				getScore(player1, player2, board, score);
				board=new ChessBoardGUI();
				board.addAllPieces();
				startGame(board, score,player1,player2);
			}
			else{
				System.out.println("playing");
			}
		}
	}

	private static void getScore(String player1, String player2,
			ChessBoardGUI board, HashMap score) {
		String winner=board.getWinner();
		if(winner.equals("Player1")){
			String currScore=(String) score.get(player1+player2);
			if(score.get(player1+player2)==null)
				score.put(player1+player2, "0:0");
			else{
				String newScore=updateScore(currScore,1);
				score.put(player1+player2, newScore);
			}
		}else if(winner.equals("Player2")){
			String currScore=(String) score.get(player1+player2);
			System.out.println(currScore +"name "+player1+player2);
			if(score.get(player1+player2)==null)
				score.put(player1+player2, "0:0");
			else
			{	String newScore=updateScore(currScore,1);
				score.put(player1+player2, newScore);
		
			}
		}	
	}

	private static String updateScore(String currScore,int player) {
		String[] parts = currScore.split(":");
		String part1 = parts[0]; 
		String part2 = parts[1]; 
		String value;
		if(player==1){
			int curr=Integer.parseInt(part1);
			curr+=1;
			value=curr+":"+part2;
		}
		else{
			int curr=Integer.parseInt(part1);
			curr+=1;
			value=part1+":"+curr;
		}
			
		return value;
	}

	private static void startGame(ChessBoardGUI board, HashMap score,String player1,String player2) {
		
		int playerTurn=0;
		do{
			player1=board.getPlayer1();
			player2=board.getPlayer2();
		System.out.println("Player1 "+player1+" Player2 "+player2);
		}while(player1.length()<1  &&player2.length()<1);
		//this is the first time the players are playing
		if(score.get(player1+player2)==null){
			score.put(player1+player2,"0:0");
			System.out.println("put" +" "+player1+player2);
			board.setGameStatus(true);
			board.setScore("0:0");
			board.setTurn(-1);
			playerTurn=-1;
		}
		else{
			String currScore=(String) score.get(player1+player2);
			board.setGameStatus(true);
			board.setScore(currScore);
			board.setTurn(1);
			playerTurn=1;
		}
	}

}
