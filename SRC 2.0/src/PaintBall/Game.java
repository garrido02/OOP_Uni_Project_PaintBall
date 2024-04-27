/**
 * @author Francisco Correia & Sérgio Garrido
 */


package PaintBall;
import DataStructures.*;

/**
 * Interface Game responsible for creating all the Game Class prototypes.
 */
public interface Game {

    /**
     * Returns the current Game Status.
     * @return true if the is running. Otherwise, returns false.
     */
	boolean getStatus();

    /**
     * Initializes the game after validating all the necessary information.
     * @param width The number of columns of the map
     * @param height The number of rows of the map
     * @param teamsNr The number of teams
     * @param bunkersNr The number of bunkers
     * @pre width >= 10 && height >= 10 && teamsNr >= 2 && bunkersNr >= teamsNr
     */
	void initGame(int width, int height, int teamsNr, int bunkersNr);

    /**
     * Returns the teams who's current turn belongs to.
     * @return The name of the current team
     */
    String getCurrentTeam();


    boolean isValidPosition(int x, int y);
    boolean isInside(int x, int y);
    boolean hasBunker(String name);
    boolean hasTeam(String name);
    boolean isAbandonedBunker(String name);
    boolean isBunkerFromCurrentTeam(String name);
    boolean isBunkerFree(String name);
    
    void addBunker(int x, int y, String name, int treasury);
    void addTeam(String teamName, String bunkerName);
    boolean addPlayer(String playerType, String bunkerName);
    
    boolean isExistingType(String playerType);
    boolean isValidDirection(String dir);
    
    
    // void setCurrentTeam();
    boolean hasEnoughTeams();
    boolean isMovementPossible(String dir, int x, int y);
    
    boolean isValidMovement(int x, int y, int nrMoves);
    boolean isPlayer(int x, int y);
    
    
    void cancelGame();
    int getRows();
    int getCols();
    int getBunkersNr();
    int activeTeamsNr();
    int getCurrentTeamPlayersNumber();
    int getCurrentTeamBunkerNumber();
    
    void nextTurn();

    Iterator<Move> move(int x, int y, String nextLine);
    
    Iterator <Move> moveIterator();
    Iterator<Player> playerIterator();
    Iterator<Team> activeTeamsIterator();
    Iterator<Bunker> bunkerIterator();
    Iterator<Bunker> currentTeamBunkerIterator();
    Iterator<MapElement> mapIterator();

	boolean canMove(int x, int y, String dir);
}

