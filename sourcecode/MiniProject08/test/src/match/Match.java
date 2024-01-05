package match;

import java.util.ArrayList;
import java.util.Scanner;

import boardgame.BoardGame;
import player.Player;
import screen.PlayScreenController;
import squares.*;

public class Match {
	public static Scanner scanner = new Scanner(System.in);
    private ArrayList<Player> player = new ArrayList<Player>();
    private int turn=1;
    private int direction;
    private BoardGame board = new BoardGame();
    private int squareId=0;
    
    public int squareId() {
    	return squareId;
    }
    public int getTurn() {
    	return turn;
    }
    public int getPlayerPoint(int id) {
    	return player.get(id-1).getScore();
    }
	public Match() {
		Player player1= new Player(1);
		Player player2= new Player(2);
		player.add(player1);
		player.add(player2);
		}
	public void spreadGems() {
		
		NormalSquare square=(NormalSquare) board.getSquare(squareId);
		int gemInHand = square.getNumberOfSmallGems();
		square.resetNumberOfGems();
		
		for(int i=1;i<=gemInHand;i++) {
		squareId+=direction;
		squareId=convertSquareId(squareId);
		board.getSquare(squareId).spreadGems();
		}
		PlayScreenController.spreadGems(gemInHand);
		
		if(stopSpreadGem()==false) {
			squareId+=direction;
			squareId=convertSquareId(squareId);
			spreadGems();
		};
		
	}
	    
	public boolean stopSpreadGem() {
		squareId+=direction;
		squareId=convertSquareId(squareId);
		if(board.getSquare(squareId) instanceof HalfCircle) return true;
	    if(board.getSquare(squareId).getPoint()!=0) return false;
	    squareId-=direction;
	    squareId=convertSquareId(squareId);
		while (true){
		squareId+=2*direction;
		squareId=convertSquareId(squareId);
		if(board.getSquare(squareId).getPoint()==0)
			return true;
		squareId-=direction;
		squareId=convertSquareId(squareId);
		if(board.getSquare(squareId).getPoint()!=0)
		return true;
		else 
		{   squareId+=direction;
		    squareId=convertSquareId(squareId);
		    int pointEarn=board.getSquare(squareId).getPoint();
			player.get(turn-1).increScore(pointEarn);
			board.getSquare(squareId).resetNumberOfGems();
			if(squareId==7||squareId==1) {
				HalfCircle halfcircle = (HalfCircle) board.getSquare(squareId);
				halfcircle.resetNumberOfGems();
			}
			PlayScreenController.getGemsInSquare(squareId);
		}
		}	
	}
	public int convertSquareId(int id) {
		if(id==12) return 0;
		if(id==-1)  return 11;
		return id;
	}
	public void selectSquare(int id) {   
	  squareId = id;
	}
	
	public void selectDirection(int choice) {
		 this.direction=choice;
	}
	public boolean stopMatch() {
		return board.rowNoBigGem();
	}
	public void newTurn(int turn) {
		this.turn = turn;
	}
	public void newTurn() {
		if(turn==1)
			turn = 2;
		else turn = 1;
	}
	public void showResult() {
		System.out.println("");
		System.out.println("player 1 score: "+ player.get(0).getScore());
		System.out.println("player 2 score: "+ player.get(1).getScore());
	}
	public void begin() {
		while(true)
		{   System.out.println("************** Player 1 turn: **************");
		    if (board.rowNoSmallGem(turn)==true) {
		    	player.get(1).decreScore(5);
		    	for(int i=2;i<=6;i++) {
		    		board.getSquare(i).spreadGems();
		    	}
		    }
			//selectSquare();
			//selectDirection();
		    spreadGems();//spreadGems(squareId, player.get(0));
			newTurn();
			showResult();
			squareId=0;
			if(stopMatch()==true) break;
			System.out.println("************** Player 2 turn: **************");
			 if (board.rowNoSmallGem(turn)==true) {
				 	player.get(1).decreScore(5);
			    	for(int i=8;i<=12;i++) {
			    		board.getSquare(i).spreadGems();
			    	}
			    }
			//selectSquare();
			//selectDirection();
			spreadGems();//spreadGems(squareId, player.get(1));
			newTurn();
			showResult();
			squareId=0;
			if(stopMatch()==true) break;
			
		}
	}

	public BoardGame getBoard() {
		return board;
	}
	
		public static void main(String[] args) {
			Match match=new Match();
			match.begin();
		}

	}



