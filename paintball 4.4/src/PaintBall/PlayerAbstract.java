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

	protected void updatePosition(){
		int i = movements.length - 1;
		x = movements[i].getX();
		y = movements[i].getY();
	}
}
