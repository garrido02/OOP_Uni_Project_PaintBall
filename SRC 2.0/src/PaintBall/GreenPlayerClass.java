package PaintBall;

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
}
