package PaintBall;

public class BluePlayerClass extends PlayerAbstract {

	public static final String TYPE = "BLUE";
	private static final int COST = 2;
	
	public BluePlayerClass(int x, int y, Team team) {
		super(x, y, team);
		this.cost = COST;
		this.type = TYPE;
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub

	}



}
