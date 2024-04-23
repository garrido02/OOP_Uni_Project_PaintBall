package PaintBall;

public class RedPlayerClass extends PlayerAbstract {

	public static final String TYPE = "RED";
	private static final int COST = 4;
	
	public RedPlayerClass(int x, int y, Team team) {
		super(x, y, team);
		this.cost = COST;
		this.type = TYPE;
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub

	}



}
