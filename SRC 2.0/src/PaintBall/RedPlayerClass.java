package PaintBall;

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
}
