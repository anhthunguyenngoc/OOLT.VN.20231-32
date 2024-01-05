package squares;
import gem.*;
public class HalfCircle extends Squares{

	private int numberOfBigGems = 1;	
	public HalfCircle() {
		super();
	}
	
	public void resetNumberOfGems(){
		numberOfBigGems = 0;
	}
	
	@Override
	public int getPoint() {
		BigGem bigGem = new BigGem();
		SmallGem smallGem = new SmallGem();
		int point = numberOfBigGems*bigGem.getPoint() +
				numberOfSmallGems*smallGem.getPoint();
		return point;
	}
	
	
}
