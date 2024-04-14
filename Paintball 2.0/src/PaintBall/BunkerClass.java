package PaintBall;
import java.util.Objects;

public class BunkerClass implements Bunker, MapElement {
    private static final char SYMBOL = 'B';
    private String name;
    private Team team;
    private int x;
    private int y;
    private int treasury;

    public BunkerClass(int x, int y, String name, int treasury){
        this.x = x;
        this.y = y;
        this.name = name;
        this.team = null;
        this.treasury = treasury;
    }

    @Override
    public Team getTeam() {
        return this.team;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAbandoned() {
        return team == null;
    }

    @Override
    public void changeTeam(Team t) {
        team = t;
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
        return x == other.x && y == other.y && treasury == other.treasury && Objects.equals(name, other.name) && Objects.equals(team, other.team);
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

}
