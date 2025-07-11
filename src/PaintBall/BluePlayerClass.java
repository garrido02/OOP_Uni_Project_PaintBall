/**
 * @author Francisco Correia & Sérgio Garrido
 */


package PaintBall;
import DataStructures.Array;
import DataStructures.ArrayClass;


/**
 * Class BluePlayer which extends the PlayerAbstract Class.
 */
public class BluePlayerClass extends PlayerAbstract {
	private static final int MAX_MOVES = 1;
	public static final String TYPE = "BLUE";
	private static final int COST = 2;

	/**
	 * Constructor
	 * @param x the horizontal position
	 * @param y the vertical position
	 * @param team the team to which the player belongs to
	 */
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

	@Override
	public boolean attack(MapElement element) {
		if (element instanceof GreenPlayerClass){
			((GreenPlayerClass) element).eliminate();
		} else if (element instanceof RedPlayerClass) {
			this.eliminate();
		} else {
			((BluePlayerClass) element).eliminate();;
		}
		return isAlive;
	}
}
