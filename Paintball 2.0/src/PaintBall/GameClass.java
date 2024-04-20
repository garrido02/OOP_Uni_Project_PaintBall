package PaintBall;
import DataStructures.*;

public class GameClass implements Game {
	
	private static final int MINIMUM_TEAMS = 2;
    private static final char FIELD_FREE = '.';
    private static final char BUNKER_FREE = 'B';
    private static final char BUNKER_OCCUPIED = 'O';
    
    private static final String player_RED = "RED";
    private static final String player_BLUE = "BLUE";
    private static final String player_GREEN = "GREEN";

    private boolean status;
    private Team currentTeam;
    private char[][] map;
    private MapElement[][] mapE;
    private TeamsCollection teams;
    private BunkersCollection bunkers;

    // PlaceHolder
    public GameClass(){
        status = false;
    }

    public void initGame(int width, int height, int teamsNr, int bunkersNr){
        status = true;
        map = new char[width][height];
        mapE = new MapElement[width][height];
        bunkers = new BunkersCollectionClass(bunkersNr);
        teams = new TeamsCollectionClass(teamsNr);
        fillMap();
    }
    
    

    @Override
    public int getBunkersNr() {
        return bunkers.getBunkersNr();
    }

    @Override
    public int activeTeamsNr() {
        return teams.activeTeams();
    }

    @Override
    public Iterator<Bunker> bunkerIterator() {
        return bunkers.iterator();
    }

    @Override
    public Iterator<Team> activeTeamsIterator() {
        return teams.iterator();
    }

    @Override
    public void cancelGame() {
        status = false;
    }

    @Override
    public int getCols() {
        return map.length;
    }

    @Override
    public int getRows() {
        return map[0].length;
    }

    private void fillMap(){
    	MapElement empty = new EmptyElementClass();
        for (int i = 0; i < mapE.length; i++){
            for (int j = 0; j < mapE[0].length; j++){
                map[i][j] = FIELD_FREE;
                mapE[i][j] = empty; 
            }
        }
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public String getCurrentTeam() {
        return currentTeam.getName();
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        return isInside(x,y) && map[x][y] == FIELD_FREE;
    }
    
    private boolean isInside(int a, int b) {
    	return (a >= 0 && a <= map[0].length) && (b >= 0 && b <= map.length);
    }

    @Override
    public boolean hasBunker(String name) {
        return bunkers.hasBunker(name);
    }

    @Override
    public void addBunker(int x, int y, String name, int treasury) {
        map[x][y] = BUNKER_FREE;
        Bunker bunker = new BunkerClass (x,y,name,treasury);
        bunkers.addBunker(bunker);
        mapE[x][y] = bunker;
    }

    @Override
    public void addTeam(String teamName, String bunkerName) {
    	Team team = new TeamClass(teamName,bunkerName);
        teams.addTeam(team);
        bunkers.conquerBunker(team, bunkerName);
    }

    @Override
    public boolean hasTeam(String name) {
        return teams.hasTeam(name);
    }

    @Override
    public boolean isAbandonedBunker(String name) {
        return bunkers.isAbandoned(name);
    }

    @Override
    public void setCurrentTeam() {
       currentTeam = teams.getCurrentTeam();
    }

	@Override
	public boolean hasEnoughTeams() {
		return teams.getSize() >= MINIMUM_TEAMS;
	}
	
	public Iterator<MapElement> mapIterator() {
		return new MapElementIterator(mapE,currentTeam);
	}

	@Override
	public boolean addPlayer(String playerType, String bunkerName) {
			return bunkers.createPlayer(playerType, bunkerName);
	}
	
	public boolean isExistingType(String playerType) {
		return(playerType.equalsIgnoreCase(player_RED) || 
				playerType.equalsIgnoreCase(player_BLUE) || 
				playerType.equalsIgnoreCase(player_GREEN));
	}
	
	public boolean isExistingType2(String playerType) {
		boolean exists = true;
		try{
			PlayerType.valueOf(playerType.toUpperCase());
		}catch (IllegalArgumentException e) {
			exists = false;
		}
		return exists;
	}

	@Override
	public boolean isBunkerFromCurrentTeam(String name) {
		return bunkers.isBunkerFromTeam(name, currentTeam);
	}

	@Override
	public boolean isBunkerFree(String name) {
		return bunkers.haveSpace(name);
	}
	
	
	
}
