/**
 * @author Francisco Correia & Sérgio Garrido
 */


package PaintBall;
import DataStructures.Array;
import DataStructures.ArrayClass;


/**
 * Class GreenPlayer which extends the PlayerAbstract Class.
 */
public class GreenPlayerClass extends PlayerAbstract {
	private static final int MAX_MOVES = 1;
	public static final String TYPE = "GREEN";
	private static final int COST = 2;

	/**
	 * Constructor
	 * @param x the horizontal position
	 * @param y the vertical position
	 * @param team the team to which the player belongs to
	 */
	public GreenPlayerClass(int x, int y, Team team) {
		super(x, y, team);
		this.cost = COST;
		this.type = TYPE;
		super.movements = new Move[MAX_MOVES];
		this.possibleMoves = MAX_MOVES;
	}

	@Override
	public Array<Coords> attackPattern(int rows, int cols) {
		int i = this.getX();
		int j = this.getY();
		int nextPosX = i;
		int nextPosY = j;
 		Array<Coords> pattern = new ArrayClass<>();
		int nrSteps = 1;
		while (i - nrSteps >= 0 || i + nrSteps < cols || j - nrSteps >= 0 || j + nrSteps < rows){
			if (i - nrSteps >= 0 && j - nrSteps >= 0){
				nextPosX = i - nrSteps;
				nextPosY = j - nrSteps;
				pattern.insertLast(new CoordsClass(nextPosX, nextPosY));
			}
			if (i + nrSteps < cols - 1 && j - nrSteps >= 0){
				nextPosX = i + nrSteps;
				nextPosY = j - nrSteps;
				pattern.insertLast(new CoordsClass(nextPosX, nextPosY));
			}
			if (i - nrSteps >= 0 && j + nrSteps < rows){
				nextPosX = i - nrSteps;
				nextPosY = j + nrSteps;
				pattern.insertLast(new CoordsClass(nextPosX, nextPosY));
			}
			if (i + nrSteps < cols - 1 && j + nrSteps < rows){
				nextPosX = i + nrSteps;
				nextPosY = j + nrSteps;
				pattern.insertLast(new CoordsClass(nextPosX, nextPosY));
			}
			nrSteps++;
		}
		return pattern;
	}

	@Override
	public boolean attack(MapElement element) {
		if (element instanceof RedPlayerClass){
			((RedPlayerClass) element).eliminate();
		} else if (element instanceof BluePlayerClass) {
			this.eliminate();
		} else {
			((GreenPlayerClass) element).eliminate();;
		}
		return isAlive;
	}
}
