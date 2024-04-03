package PaintBall;

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
}
