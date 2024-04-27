package PaintBall;
import java.util.Objects;

public class BunkerClass implements Bunker, MapElement {
	
	private static final String NO_TEAM = "without owner";
    private static final char SYMBOL = 'B';
    private static final char OCCUPIED_SYMBOL = 'O';
    
    private static final String player_RED = "RED";
    private static final String player_BLUE = "BLUE";
    private static final String player_GREEN = "GREEN";
    
    private String name;
    private Player player;
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
    
    
    
    public boolean createPlayer(String playerType) {
    	Player tmp_Player = null;
    	
    	switch(PlayerType.valueOf(playerType)) {

			case BLUE -> {
				tmp_Player = new BluePlayerClass(getX(),getY(),team);
			}
			case RED -> {
				tmp_Player = new RedPlayerClass(getX(),getY(),team);
			}
			case GREEN -> {
				tmp_Player = new GreenPlayerClass(getX(),getY(),team);
			}
		
    	}
        return hasEnoughCoins(tmp_Player);
    }
    
    private boolean hasEnoughCoins(Player player) {
    	boolean created = false;
    	if(player != null && this.treasury >= player.cost())
		{
    		this.player = player;
        	team.addPlayer(player);
            this.treasury -= player.cost();
        	created = true;
		};   	
    	return created;
    }

    @Override
    public void playerOut() {
    	player = null;
    }

    @Override
    public void playerIn(Player player){
        this.player = player;
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
		char symbol = OCCUPIED_SYMBOL;		
		if(player == null)
			symbol = SYMBOL;	
		return symbol;
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
	public boolean isFree() {
		return player == null;
	}

    @Override
    public int getTreasury() {
        return treasury;
    }

	@Override
	public String getTeamName() {
		String teamName = NO_TEAM;
		if(team != null)
			teamName = team.getName();
		
		return teamName;
	}


    @Override
	public Move move(String dir) {
		Move move = player.move(dir);
		playerOut();
		return move;
	}
}
