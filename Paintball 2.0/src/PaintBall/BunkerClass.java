package PaintBall;
import java.util.Objects;

public class BunkerClass implements Bunker, MapElement {
    private static final String ABANDONED = "without owner";
    private static final char SYMBOL = 'B';
    private String name;
    private String teamOwner;
    private int x;
    private int y;
    private int treasury;

    public BunkerClass(int x, int y, String name, int treasury){
        this.x = x;
        this.y = y;
        this.name = name;
        this.teamOwner = ABANDONED;
        this.treasury = treasury;
    }

    @Override
    public String getOwner() {
        return this.teamOwner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAbandoned() {
        return teamOwner.equals(ABANDONED);
    }

    @Override
    public void changeOwner(String name) {
        teamOwner = name;
    }

    @Override
    public void incrementTreasury() {
        treasury++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        BunkerClass other = (BunkerClass) o;
        return x == other.x && y == other.y && treasury == other.treasury && Objects.equals(name, other.name) && Objects.equals(teamOwner, other.teamOwner);
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
		return teamOwner;
	}
}
