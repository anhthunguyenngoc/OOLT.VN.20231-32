package player;

public class Player {
private int score;
public int playerID;
	public Player(int playerID) {
		this.playerID=playerID;
	}
    public void increScore(int point) {
    	this.score+=point;
    }
    public void decreScore(int point) {
    	this.score-=point;
    }
    public int getScore() {
    	return this.score;
    }
}
