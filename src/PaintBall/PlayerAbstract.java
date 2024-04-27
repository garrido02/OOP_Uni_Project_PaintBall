package PaintBall;
import DataStructures.*;

abstract class PlayerAbstract implements Player, MapElement {

	private static final char SYMBOL = 'P';
	private Team team;
	private int x;
	private int y;
	protected int cost;
	protected String type;
	protected Move[] movements;
	protected int size;
	
	protected PlayerAbstract(int x, int y, Team team) {
		this.x = x;
		this.y = y;
		this.team = team;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public int cost() {
		return cost;
	}
	
	public char getChar() {
		return SYMBOL;
	}
	
	public String getType() {
		return type.toLowerCase();
	}

	protected void updatePosition(String dir){
		int[] newCoords = MoveClass.calculateMove(x, y, dir);
		x = newCoords[0];
		y = newCoords[1];
	}

	@Override
	public Move move(String dir) {
		updatePosition(dir);
		return new MoveClass(this, MoveOutput.SUCCESS);
	}

	@Override
	public boolean attack(MapElement element) {
		return false;
	}

	public Iterator<Move> iterator(){
		return new ArrayIteratorClass<Move>(movements,size);
	}
}
