/**
 * @author Francisco Correia & Sérgio Garrido
 */


package PaintBall;
import DataStructures.Iterator;


/**
 * Class Map which implements the Map interface.
 */
public class MapClass implements Map{
	private static final MapElement EMPTY = new EmptyElementClass();
    private MapElement[][] mapE;
    private int cols;
    private int rows;


    /**
     * Constructor.
     * @param width the number of columns of the map
     * @param height the number of rows of the map
     */
    public MapClass(int width, int height){
        this.cols = width;
        this.rows = height;
        mapE = new MapElement[cols][rows];
        fillMap();
    }

    /**
     * Fills the map with empty cells allowing the game to start anew.
     */
    private void fillMap(){
    
        for (int i = 0; i < mapE.length; i++){
            for (int j = 0; j < mapE[0].length; j++){
               mapE[i][j] = EMPTY;
            }
        }
    }

    @Override
    public void addElement(MapElement e, int x, int y) {
        mapE[x][y] = e;
    }

    @Override
    public int getHeight() {
        return rows;
    }

    @Override
    public int getWidth() {
        return cols;
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        return isInside(x, y) && isEmpty(x, y);
    }


    @Override
    public Iterator<MapElement> iterator(Team team) {
        return new MapElementIterator(mapE, team, EMPTY);
    }

    /**
     * Checks if a map coordinate (x,y) is an empty cell.
     * @param x the horizontal position
     * @param y the vertical position
     * @return true if the map cell is empty. Otherwise, false.
     */
    private boolean isEmpty(int x, int y){
        return (mapE[x][y] instanceof EmptyElementClass);
    }

    @Override
    public boolean isInside(int a, int b) {
        boolean validRow = b >= 0 && b < this.rows;
        boolean validCol = a >= 0 && a < this.cols;
        return validRow && validCol;
    }

	@Override
	public Move moveElem(int x, int y, Move newPos) {
        MapElement currentCell = mapE[x][y];
        MapElement nextCell = mapE[newPos.getX()][newPos.getY()];
        Move result;
        if (currentCell instanceof Player){
            result = processMove(currentCell, nextCell, newPos, x, y);
        } else {
            result = processMove(currentCell, nextCell, newPos, x, y);
        }
        return result;
	}

    /**
     * Processes the move command.
     * @param currentCell the current map cell the player is at
     * @param nextCell the cell to which the player desires to move
     * @param newPos a Move object with all information regarding the new position of the player
     * @param x the horizontal position
     * @param y the vertical position
     * @return a Move object with all information regarding the movement
     */
    private Move processMove(MapElement currentCell, MapElement nextCell, Move newPos, int x, int y) {
        Player p = newPos.getPlayer();
        Move result;
        if (nextCell instanceof EmptyElementClass) {
            addElement(p, newPos.getX(), newPos.getY());
            updateMap(currentCell, x, y);
            result = new MoveClass(p, MoveOutput.SUCCESS);
        } else if (nextCell instanceof Bunker) {
            Bunker b = (Bunker) nextCell;
            if (p.getTeam().equals(b.getTeam())) {
                b.playerIn(p);
                updateMap(currentCell, x, y);
                result = new MoveClass(p, MoveOutput.SUCCESS);
            } else {
                if (b.isFree()) {
                	updateBunkersTeams(b,p);
                    updateMap(currentCell, x, y);
                    result = new MoveClass(p, MoveOutput.BUNKER_SEIZED);
                } else {
                    if (p.attack(b.getPlayer())) {
                    	b.getTeam().removePlayer(b.getPlayer());
                    	updateBunkersTeams(b,p);
                        result = new MoveClass(p, MoveOutput.WON_FIGHT_AND_BUNKER_SEIZED);
              
                    } else {
                        result = new MoveClass(p, MoveOutput.PLAYER_ELIMINATED);
//                        if(mapE[p.getX()][p.getY()] instanceof Bunker) {
//                        	((Bunker)mapE[p.getX()][p.getY()]).changeTeam(nextCell.getTeam());
//                        	((Bunker)mapE[p.getX()][p.getY()]).playerOut();
//                        }
                        p.getTeam().removePlayer(p);
                    }
                    updateMap(currentCell, x, y);
                }
            }
        } else {
            if (p.attack(nextCell)) {
                result = new MoveClass(p, MoveOutput.WON_FIGHT);
                nextCell.getTeam().removePlayer((Player) nextCell);
                addElement(p, newPos.getX(), newPos.getY());
            } else {
                result = new MoveClass(p, MoveOutput.PLAYER_ELIMINATED); 
//                if(mapE[p.getX()][p.getY()] instanceof Bunker) {
//                	((Bunker)mapE[p.getX()][p.getY()]).changeTeam(nextCell.getTeam());
//                	((Bunker)mapE[p.getX()][p.getY()]).playerOut();
//                }
                p.getTeam().removePlayer(p);
            }
            updateMap(currentCell, x, y);
        }
        return result;
    }

    /**
     * Updates the current map after a movement has been made.
     * @param element the map element to be updated
     * @param x the horizontal position
     * @param y the vertical position
     */
    private void updateMap(MapElement element, int x, int y){
        if (element instanceof Player){
            removeElement(x, y);
        } else if (element instanceof Bunker){
            Bunker b = (Bunker) element;
            b.playerOut();
        }
    }

    @Override
    public void removeElement(int x, int y) {
        mapE[x][y] = EMPTY;
    }

    /**
     * Updates the team who owns a given bunker.
     * @param bunker the bunker whose team is about to change
     * @param player the player which belongs to the team who will own the bunker
     */
    private void updateBunkersTeams(Bunker bunker, Player player) {
    	if (!bunker.isAbandoned()){
            bunker.getTeam().removeBunker(bunker);
        }
    	bunker.changeTeam(player.getTeam());
        bunker.playerIn(player);
        bunker.getTeam().conquerBunker(bunker);
    }

	public boolean nextCellOccupied(int x, int y, String dir) {
		
		int[] nextCoords = MoveClass.calculateMove(x, y, dir);
		MapElement player = mapE[x][y];
		MapElement cell = mapE[nextCoords[0]][nextCoords[1]];
		if((cell instanceof Player) && player.getTeam().equals(cell.getTeam())) {
			return true;
		}
		return false;
	}

    @Override
    public MapElement getElement(int x, int y) {
        return mapE[x][y];
    }
}
