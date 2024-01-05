package squares;
import gem.*;

public class NormalSquare extends Squares{

	
	
	public NormalSquare() {
		super();
		numberOfSmallGems = 5;
	}
	

	@Override
	public int getPoint() {
		SmallGem smallGem = new SmallGem();
		return smallGem.getPoint()*numberOfSmallGems;
		
	}
}