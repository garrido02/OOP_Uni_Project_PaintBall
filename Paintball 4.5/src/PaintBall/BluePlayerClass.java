package PaintBall;

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
	}

	@Override
	public MoveOutput move(String dir) {
		movements[0] = new MoveClass(this, dir);
		updatePosition();
		return MoveOutput.SUCCESS;
	}
}
