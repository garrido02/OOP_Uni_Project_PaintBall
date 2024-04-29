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

    /**
     * Checks if a given position is valid.
     * @param x The horizontal position
     * @param y The vertical position
     * @return true if the position is valid. Otherwise, false.
     */
    boolean isValidPosition(int x, int y);

    /**
     * Checks if a given position is in bounds.
     * @param x The horizontal position
     * @param y The vertical position
     * @return true if the position is in bounds. Otherwise, false.
     */
    boolean isInside(int x, int y);

    /**
     * Checks if a bunker with a given name already exists.
     * @param name The name of a bunker we wish to create
     * @return true if the bunker already exists. Otherwise, false.
     */
    boolean hasBunker(String name);

    /**
     * Checks if a team with a given name already exists.
     * @param name The name of a team we wish to create
     * @return true if the team already exists. Otherwise, false.
     */
    boolean hasTeam(String name);

    /**
     * Checks if a bunker with a given name is abandoned.
     * @param name The name of the bunker
     * @return true if the bunker is abandoned. Otherwise, false.
     */
    boolean isAbandonedBunker(String name);

    /**
     * Checks if the bunker is owned by the current team.
     * @param name The name of a given team
     * @return true if the bunker belongs to the current team. Otherwise, false.
     */
    boolean isBunkerFromCurrentTeam(String name);

    /**
     * Checks if a bunker has no players inside, i.e, is free.
     * @param name The name of the bunker.
     * @return true if the bunker is free. Otherwise, false.
     */
    boolean isBunkerFree(String name);

    /**
     * Adds a new bunker to the game.
     * @param x The horizontal position of the bunker
     * @param y The vertical position of the bunker
     * @param name The name of the bunker
     * @param treasury The bunker's initial amount of coins
     * @pre treasury > 0 && name != null && isValidPosition(x, y) && !hasBunker(name)
     */
    void addBunker(int x, int y, String name, int treasury);

    /**
     * Adds a new team to the game.
     * @param teamName The name of the team
     * @param bunkerName The name of the bunker owned by the team
     * @pre !hasTeam(teamName) && hasBunker(bunkerName) && isAbandonedBunker(bunkerName)
     */
    void addTeam(String teamName, String bunkerName);

    /**
     * Creates a player of the current team inside a bunker owned by that team.
     * @param playerType The type of player we wish to create
     * @param bunkerName The name of the bunker we wish the player to spawn in
     * @return true if the player is created. Otherwise, false.
     * @pre isExistingType(playerType) && hasBunker(bunkerName) && isBunkerFromCurrentTeam(bunkerName) && isBunkerFree(bunkerName)
     */
    boolean addPlayer(String playerType, String bunkerName);

    /**
     * Checks if a given player type exists
     * @param playerType The type of player we wish to check
     * @return true if the type of player exists. Otherwise, false.
     */
    boolean isExistingType(String playerType);


    boolean isValidDirection(String dir);


    /**
     * Checks if the game has enough teams to be carried out.
     * @return true if enough teams. Otherwise, false.
     */
    boolean hasEnoughTeams();


    boolean isMovementPossible(String dir, int x, int y);
    
    boolean isValidMovement(int x, int y, int nrMoves);

    /**
     * Checks if a given position is occupied by a player.
     * @param x The horizontal position
     * @param y The vertical position
     * @return true if the position is occupied by a player. Otherwise, false.
     */
    boolean isPlayer(int x, int y);

    /**
     * Cancels the current game.
     */
    void cancelGame();

    /**
     * Returns the number of rows of the map of the current game.
     * @return the number of rows of the map
     */
    int getRows();

    /**
     * Returns the number of columns of the map of the current game.
     * @return the number of columns of the map
     */
    int getCols();

    /**
     * Returns the number of bunkers available on the current game.
     * @return the number of bunkers available
     */
    int getBunkersNr();

    /**
     * Returns the number of active teams on the current game.
     * @return the number of active teams
     */
    int activeTeamsNr();

    /**
     * Returns the number of players of the current team.
     * @return the number of players of the current team
     */
    int getCurrentTeamPlayersNumber();

    /**
     * Returns the number of bunkers of the current team.
     * @return the number of bunkers of the current team
     */
    int getCurrentTeamBunkerNumber();

    /**
     * Passes the turn to the next active team.
     */
    void nextTurn();


    boolean attack();

    /**
     * Checks if the game is over.
     * @return true if the game is over. Otherwise, false.
     */
    boolean isGameOver();

    /**
     * Returns the name of the winner team.
     * @return the name of the winner team
     */
    String getWinner();

    Iterator<Move> move(int x, int y, String nextLine);
    
    Iterator <Move> moveIterator();

    /**
     * Returns a player iterator.
     * @return a player iterator
     */
    Iterator<Player> playerIterator();

    /**
     * Returns an active teams iterator.
     * @return an active teams iterator
     */
    Iterator<Team> activeTeamsIterator();

    /**
     * Returns a bunker iterator.
     * @return a bunker iterator
     */
    Iterator<Bunker> bunkerIterator();

    /**
     * Returns a bunker iterator of the current team.
     * @return a bunker iterator of the current team
     */
    Iterator<Bunker> currentTeamBunkerIterator();

    /**
     * Returns a map element iterator.
     * @return a map element iterator
     */
    Iterator<MapElement> mapIterator();

	boolean canMove(int x, int y, String dir);
}

