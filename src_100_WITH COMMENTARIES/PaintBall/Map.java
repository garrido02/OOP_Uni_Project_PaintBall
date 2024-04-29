/**
 * @author Francisco Correia & Sérgio Garrido
 */


package PaintBall;
import DataStructures.*;


/**
 * Interface Map responsible for creating all the Map class prototypes.
 */
public interface Map {

    /**
     * Returns the number of columns of the map.
     * @return the number of columns
     */
    int getWidth();

    /**
     * Returns the number of rows of the map.
     * @return the number of rows
     */
    int getHeight();

    /**
     * Checks if a pair of coordinates is valid.
     * @param x the horizontal position
     * @param y the vertical position
     * @return true if the pair of coordinates is valid. Otherwise, false.
     */
    boolean isValidPosition(int x, int y);

    /**
     * Checks if a pair of coordinates is inside the map bounds.
     * @param x the horizontal position
     * @param y the vertical position
     * @return true if the pair of coordinates is inside bounds. Otherwise, false.
     */
    boolean isInside(int x, int y);

    /**
     * Adds an element to the current map.
     * @param e the element to be added to the map
     * @param x the horizontal position
     * @param y the vertical position
     */
    void addElement(MapElement e, int x, int y);

    /**
     * Removes an element located at coordinates (x,y).
     * @param x the horizontal position
     * @param y the vertical position
     */
    void removeElement(int x, int y);

    /**
     * Returns an iterator of map elements of a given team.
     * @param team the team of which the elements to iterate belong to.
     * @return an iterator of map elements.
     */
    Iterator<MapElement> iterator(Team team);

    /**
     * Moves an element to a new position.
     * @param x the horizontal position
     * @param y the vertical position
     * @param newPos a Move object with all the information regarding the new position
     * @return a Move object with all the information regarding the movement
     */
    Move moveElem(int x, int y, Move newPos);

    /**
     * Returns the element located at coordinates(x,y)
     * @param x the horizontal position
     * @param y the vertical position
     * @return a MapElement object located at coordinates (x,y)
     */
    MapElement getElement(int x, int y);

    /**
     * Checks if a cell located at coordinates (x,y) is occupied.
     * @param x the horizontal position
     * @param y the vertical position
     * @param dir the movement direction
     * @return true if the cell is occupied. Otherwise, false.
     */
    boolean nextCellOccupied(int x, int y, String dir);
}
