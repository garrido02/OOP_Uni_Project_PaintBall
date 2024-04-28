package PaintBall;

import DataStructures.Array;
import DataStructures.ArrayClass;

public class RedPlayerClass extends PlayerAbstract {
	static final int MAX_MOVES = 3;
	public static final String TYPE = "RED";
	private static final int COST = 4;
	private int size;
	
	public RedPlayerClass(int x, int y, Team team) {
		super(x, y, team);
		this.cost = COST;
		this.type = TYPE;
		super.movements = new Move[MAX_MOVES];
		this.possibleMoves = MAX_MOVES;
		size = 0;
	}

	@Override
	public Array<Coords> attackPattern(int rows, int cols) {
		int leftPos = this.getX();
		int rightPos = this.getX();
		Array<Coords> pattern = new ArrayClass<>();
		int nrSteps = 1;
		for (int j = getY(); j < rows; j++){
			for (int i = getX(); i < cols; i++){
				if (!(i == getX() && j == getY())){
					pattern.insertLast(new CoordsClass(i, j));
				}
			}
		}
		return pattern;
	}

	@Override
	public boolean attack(MapElement element) {
		if (element instanceof BluePlayerClass){
			((BluePlayerClass) element).eliminate();
		} else if (element instanceof GreenPlayerClass) {
			this.eliminate();
		} else {
			((RedPlayerClass) element).eliminate();;
		}
		return isAlive;
	}
}
