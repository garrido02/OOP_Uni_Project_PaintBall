package PaintBall;
import DataStructures.*;

public class GameClass implements Game {
	
	private static final int NOT_FOUND = -1;
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
    //private TeamsCollection teams;
    private ArrayClass<Team> teams;
    private ArrayClass<Bunker> bunkers;

    // PlaceHolder
    public GameClass(){
        status = false;
    }

    public void initGame(int width, int height, int teamsNr, int bunkersNr){
        status = true;
        map = new char[width][height];
        mapE = new MapElement[width][height];
        bunkers = new ArrayClass<>(bunkersNr);
        teams = new ArrayClass<>(teamsNr);
        fillMap();
    }

    @Override
    public int getBunkersNr() {
        return bunkers.size();
    }

    @Override
    public int activeTeamsNr() {
        int activeTeams = 0;
        Iterator<Team> ite = teams.iterator();
        while (ite.hasNext()){
            activeTeams++;
            ite.next();
        }
        return activeTeams;
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
    	return findBunkerIdx(name) != NOT_FOUND;
    }
    
    private int findBunkerIdx(String name){
        int i = 0;
        Iterator<Bunker> ite = bunkers.iterator();
        while (ite.hasNext()){
            if (ite.next().getName().equals(name)){
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }

    @Override
    public void addBunker(int x, int y, String name, int treasury) {
        map[x][y] = BUNKER_FREE;
        Bunker bunker = new BunkerClass (x,y,name,treasury);
        bunkers.insertLast(bunker);
        mapE[x][y] = bunker;
    }

    @Override
    public void addTeam(String teamName, String bunkerName) {
        Bunker bunker = bunkers.get(findBunkerIdx(bunkerName));
    	Team team = new TeamClass(teamName);
    	if (teams.size() == 0){
             currentTeam = team;
        }
        teams.insertLast(team);
        conquerBunker(team, bunker);
        
    }
    
    private void conquerBunker(Team team, Bunker bunker) {
    	bunker.changeTeam(team);
        team.conquerBunker(bunker);
    }


    @Override
    public boolean hasTeam(String name) {
        return findTeamIdx(name) != NOT_FOUND;
    }
    
    private int findTeamIdx(String name){
        int i = 0;
        Iterator<Team> ite = teams.iterator();
        while (ite.hasNext()){
            if (ite.next().getName().equals(name)){
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }

    @Override
    public boolean isAbandonedBunker(String name) {
        return bunkers.get(findBunkerIdx(name)).isAbandoned();
    }

    //@Override
    //public void setCurrentTeam() {
    //   currentTeam = teams.getCurrentTeam();
    //}

	@Override
	public boolean hasEnoughTeams() {
		return teams.size() >= MINIMUM_TEAMS;
	}
	
	public Iterator<MapElement> mapIterator() {
		return new MapElementIterator(mapE,currentTeam);
	}

	@Override
	public boolean addPlayer(String playerType, String bunkerName) {
			boolean result = false;
			if(bunkers.get(findBunkerIdx(bunkerName)).createPlayer(playerType)) {
				changeCurrentTeam();
				result = true;
			}
			return result;
	}

    private void changeCurrentTeam(){
        int i = findTeamIdx(currentTeam.getName());
        do {
            i = (i + 1) % teams.size();
        } while (!teams.get(i).isActive());
        currentTeam = teams.get(i);
    }

	/*public boolean isExistingType(String playerType) {
		return(playerType.equalsIgnoreCase(player_RED) || 
				playerType.equalsIgnoreCase(player_BLUE) || 
				playerType.equalsIgnoreCase(player_GREEN));
	}*/
	
	public boolean isExistingType(String playerType) {
		boolean exists = true;
		try{
			PlayerType.valueOf(playerType);
		}catch (IllegalArgumentException e) {
			exists = false;
		}
		return exists;
	}

	@Override
	public boolean isBunkerFromCurrentTeam(String name) {
		return bunkers.get(findBunkerIdx(name)).getTeam().equals(currentTeam);
	}

	@Override
	public boolean isBunkerFree(String name) {
		return bunkers.get(findBunkerIdx(name)).isFree();
	}
	
	public Iterator<Player> playerIterator(){
		return currentTeam.playerIterator();
	}

    @Override
    public Iterator<Bunker> currentTeamBunkerIterator() {
        return currentTeam.bunkerIterator();
    }

    @Override
    public int getCurrentTeamPlayersNumber() {
		return currentTeam.numberPlayer();
	}

    @Override
    public int getCurrentTeamBunkerNumber() {
        return currentTeam.numberBunkers();
    }
}
