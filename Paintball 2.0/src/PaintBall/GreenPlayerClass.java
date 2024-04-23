package PaintBall;

public class GreenPlayerClass extends PlayerAbstract {

	public static final String TYPE = "GREEN";
	private static final int COST = 2;
	
	public GreenPlayerClass(int x, int y, Team team) {
		super(x, y, team);
		this.cost = COST;
		this.type = TYPE;
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub

	}



}
