package match;

import java.util.ArrayList;
import java.util.Scanner;

import boardgame.BoardGame;
import player.Player;
import screen.MenuScreen;
import screen.PlayScreenController;
import squares.*;

public class Match {
	public static Scanner scanner = new Scanner(System.in);
    private ArrayList<Player> player = new ArrayList<Player>();
    private int turn=1;
    private int direction;
    private BoardGame board = new BoardGame();
    private int gemInHand;
    private int squareId;
    
    public int getSquareId() {
    	return squareId;
    }
    public int getDirection() {
    	return direction;
    }
    public int getTurn() {
    	return turn;
    }
    public int getPlayerPoint(int id) {
    	return player.get(id-1).getScore();
    }
    public int getGemInHand() {
    	return gemInHand;
    }
	public Match() {
		Player player1= new Player(1);
		Player player2= new Player(2);
		player.add(player1);
		player.add(player2);
		}
	
	public void getGemsInSquare(int squareId) {
		NormalSquare square=(NormalSquare) board.getSquare(squareId);
		gemInHand = square.getNumberOfSmallGems();
		square.resetNumberOfGems();
	}
	
	public void getGemsToPoint(int squareId) {
		board.getSquare(squareId).resetNumberOfGems();
		
		if(squareId==7||squareId==1) {
			HalfCircle halfcircle = (HalfCircle) board.getSquare(squareId);
			halfcircle.resetNumberOfGems();
		}
	}
	
	public void spreadGems(int squareId) {
		board.getSquare(squareId).spreadGems();
	}
	
	public int stopSpreadGem(int squareId) {
		
		if(board.getSquare(squareId) instanceof HalfCircle) return -1;	//stop true 		//ô liền cuối là ô Quan
	    if(board.getSquare(squareId).getPoint()!=0) return 0;			//stop false		//ô liền cuối có Dân
	    if(board.getSquare(squareId).getPoint()==0 && board.getSquare(squareId+1).getPoint()==0 ) return -1; //2 ô trống liền kề
	    
	    //Ô liền cuối trống
	    return 1; //player get point
	}
	
	public void getPoint(int squareId) {
		
		getGemsToPoint(squareId);
		
		int pointEarn=board.getSquare(squareId).getPoint();
		player.get(turn-1).increScore(pointEarn);
	
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
		    //spreadGems();//spreadGems(squareId, player.get(0));
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
			//spreadGems();//spreadGems(squareId, player.get(1));
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



