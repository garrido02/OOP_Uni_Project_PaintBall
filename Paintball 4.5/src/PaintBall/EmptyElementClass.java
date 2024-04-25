package PaintBall;

public class EmptyElementClass implements MapElement {

	private static final char SYMBOL = '.';
    private static final Team TEAM = null;
    private int x;
    private int y;
	
    public EmptyElementClass() {
    	this.x = 0;
    	this.y = 0;
    }
	
	@Override
	public char getChar() {
		return SYMBOL;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public Team getTeam() {
		return TEAM;
	}

	@Override
	public MoveOutput move(String dir) {
		return null;
	}

}
