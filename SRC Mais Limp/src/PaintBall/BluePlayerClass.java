package PaintBall;
import DataStructures.Array;
import DataStructures.ArrayClass;

public class BluePlayerClass extends PlayerAbstract {
	private static final int MAX_MOVES = 1;
	public static final String TYPE = "BLUE";
	private static final int COST = 2;
	
	public BluePlayerClass(int x, int y, Team team) {
		super(x, y, team);
		this.cost = COST;
		this.type = TYPE;
		super.movements = new Move[MAX_MOVES];
		this.possibleMoves = MAX_MOVES;
	}

	@Override
	public Array<Coords> attackPattern(int rows, int cols) {
		int i = this.getX();
		int leftPos = this.getX();
		int rightPos = this.getX();
		Array<Coords> pattern = new ArrayClass<>();
		int nrSteps = 1;
		while (i - nrSteps >= 0 || i + nrSteps < cols){
			if (i - nrSteps >= 0 ){
				leftPos = i - nrSteps;
				pattern.insertLast(new CoordsClass(leftPos, this.getY()));
			}
			if (i + nrSteps < cols){
				rightPos = i + nrSteps;
				pattern.insertLast(new CoordsClass(rightPos, this.getY()));
			}
			nrSteps++;
		}
		return pattern;
	}

//	@Override
//	public boolean attack(Player otherPlayer) {
//		if (otherPlayer instanceof GreenPlayerClass){
//			otherPlayer.eliminate();
//		} else if (otherPlayer instanceof RedPlayerClass) {
//			this.eliminate();
//		} else {
//			otherPlayer.eliminate();;
//		}
//		return isAlive;
//	}
}
