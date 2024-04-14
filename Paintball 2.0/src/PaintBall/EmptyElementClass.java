package PaintBall;

public class EmptyElementClass implements MapElement {

	private static final char SYMBOL = '.';
    private static final String TEAM = "EMPTY_TEAM";
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
	public String getTeamName() {
		return TEAM;
	}

}
