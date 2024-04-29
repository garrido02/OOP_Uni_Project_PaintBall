package PaintBall;

import DataStructures.Array;
import DataStructures.ArrayClass;

public class GreenPlayerClass extends PlayerAbstract {
	private static final int MAX_MOVES = 1;
	public static final String TYPE = "GREEN";
	private static final int COST = 2;
	
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

//	@Override
//	public boolean attack(Player otherPlayer) {
//		if (otherPlayer instanceof RedPlayerClass){
//			otherPlayer.eliminate();
//		} else if (otherPlayer instanceof BluePlayerClass) {
//			this.eliminate();
//		} else {
//			otherPlayer.eliminate();;
//		}
//		return isAlive;
//	}
}
