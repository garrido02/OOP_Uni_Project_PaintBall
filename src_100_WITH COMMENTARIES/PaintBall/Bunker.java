/**
 * @author Francisco Correia & Sérgio Garrido
 */


package PaintBall;

/**
 * Interface Bunker responsible for creating all the Bunker Class prototypes and which extends the MapElement interface.
 */
public interface Bunker extends MapElement{


    /**
     * Returns the name of the bunker.
     * @return the name of the bunker
     */
    String getName();


    /**
     * Checks if the bunker is abandoned.
     * @return true if is abandoned. Otherwise, false.
     */
    boolean isAbandoned();


    /**
     * Changes the owner of te bunker to a given team t.
     * @param t The new team who will own the bunker
     */
    void changeTeam(Team t);


    /**
     * Increments the amount of coins of the bunker by 1.
     */
    void incrementTreasury();

    /**
     * Creates a player of a given type.
     * @param playerType The type of player to be created
     * @return true if player is created. Otherwise, false.
     */
    boolean createPlayer(String playerType);


    /**
     * Checks if the bunker is free.
     * @return true if bunker is free. Otherwise, false.
     */
    boolean isFree();


    /**
     * Returns the amount of coins of the bunker.
     * @return the amount of coins of the bunker
     */
    int getTreasury();


    /**
     * Returns the name of the team who owns the bunker.
     * @return the name of the team who owns the bunker
     */
    String getTeamName();


    /**
     * Removes the player who is inside the bunker.
     */
    void playerOut();


    /**
     * Allows a given player to enter the bunker.
     * @param player The player who is entering the bunker
     */
    void playerIn(Player player);


    /**
     * Returns the player who is inside the bunker.
     * @return the player inside the bunker
     */
    Player getPlayer();

}
