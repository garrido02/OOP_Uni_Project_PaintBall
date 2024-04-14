package PaintBall;

abstract class PlayerAbstract implements Player, MapElement {

	private static final char SYMBOL = 'P';
	private String team;
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getTeamName() {
		return team;
	}

}
