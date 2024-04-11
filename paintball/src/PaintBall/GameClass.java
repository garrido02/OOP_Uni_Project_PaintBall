package PaintBall;
import DataStructures.*;

public class GameClass implements Game {
	
	private final int MINIMUM_TEAMS = 2;
    private final char FIELD_FREE = '.';
    private final char BUNKER_FREE = 'B';
    private final char BUNKER_OCCUPIED = 'O';

    private boolean status;
    private String currentTeam;
    private char[][] map;
    private TeamsCollection teams;
    private BunkersCollection bunkers;

    // PlaceHolder
    public GameClass(){
        status = false;
        currentTeam = "placeHolder";
    }

    public void initGame(int width, int height, int teamsNr, int bunkersNr){
        status = true;
        map = new char[width][height];
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
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = FIELD_FREE;
            }
        }
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public String getCurrentTeam() {
        return currentTeam;
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
        bunkers.addBunker(x, y, name, treasury);
        map[x][y] = BUNKER_FREE;
    }

    @Override
    public void addTeam(String teamName, String bunkerName) {
        teams.addTeam(teamName, bunkerName);
        bunkers.conquerBunker(teamName, bunkerName);
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
}
