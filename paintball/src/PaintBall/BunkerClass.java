package PaintBall;

import java.util.Objects;

public class BunkerClass implements Bunker {
    private static final String ABANDONED = "ABANDONED";
    private String name;
    private String teamOwner;
    private int x;
    private int y;
    private int treasury;

    public BunkerClass(int x, int y, String name, int treasury){
        this.x = x;
        this.y = y;
        this.name = name;
        teamOwner = ABANDONED;
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
}
