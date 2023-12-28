package boardgame;

import java.util.ArrayList;
import squares.*;

public class BoardGame {
	private ArrayList<Squares> row = new ArrayList<Squares>();
	
	public BoardGame() {
		//prepare gems to play
		HalfCircle halfCircle = new HalfCircle();
		NormalSquare normalSquare = new NormalSquare();
		row.add(halfCircle);
		row.add(normalSquare);
		row.add(normalSquare);
		row.add(normalSquare);
		row.add(normalSquare);
		row.add(normalSquare);
		row.add(halfCircle);
		row.add(normalSquare);
		row.add(normalSquare);
		row.add(normalSquare);
		row.add(normalSquare);
		row.add(normalSquare);

	}
	
	

	public boolean rowNoSmallGem(int turn) {
		if (turn == 1) {
			int sum=0;
			for (int i=2; i<=6; ++i) {
				sum+=row.get(i).getPoint();
			}
			if (sum==0) return true;
		}
		else {
			
		int sum=0;
		for (int i=8; i<=12; ++i) {
			sum+=row.get(i).getPoint();
		}
		if (sum==0) return true;
		}
		return false;
	}
    
    public boolean rowNoBigGem() {
    	if ((row.get(1).getPoint()==0) && (row.get(7).getPoint()==0))
    		return true;
    	return false;
    }
    
    public Squares getSquare(int id) {
    	return row.get(id);
    }
}