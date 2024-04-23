package PaintBall;
import DataStructures.*;

public interface Game {
	
	boolean getStatus();
	void initGame(int width, int height, int teamsNr, int bunkersNr);
    String getCurrentTeam();
    boolean isValidPosition(int x, int y);
    boolean hasBunker(String name);
    boolean hasTeam(String name);
    boolean isAbandonedBunker(String name);
    boolean isBunkerFromCurrentTeam(String name);
    boolean isBunkerFree(String name);
    
    void addBunker(int x, int y, String name, int treasury);
    void addTeam(String teamName, String bunkerName);
    boolean addPlayer(String playerType, String bunkerName);
    
    boolean isExistingType(String playerType);
    
    void setCurrentTeam();
    boolean hasEnoughTeams();
    void cancelGame();
    int getRows();
    int getCols();
    int getBunkersNr();
    int activeTeamsNr();
    int getPlayersNumber();
    
    Iterator<Player> playerIterator();
    Iterator<Team> activeTeamsIterator();
    Iterator<Bunker> bunkerIterator();
    Iterator<MapElement> mapIterator();
}

