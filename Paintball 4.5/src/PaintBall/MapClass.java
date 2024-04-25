package PaintBall;

import DataStructures.Iterator;

public class MapClass implements Map{
	private static final MapElement EMPTY = new EmptyElementClass();
    private MapElement[][] mapE;
    private int cols;
    private int rows;
    

    public MapClass(int width, int height){
        this.cols = width;
        this.rows = height;
        mapE = new MapElement[cols][rows];
        fillMap();
    }

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

    private boolean isEmpty(int x, int y){
        return (mapE[x][y] instanceof EmptyElementClass);
    }

    private boolean isInside(int a, int b) {
        boolean validRow = b >= 0 && b < this.rows;
        boolean validCol = a >= 0 && a < this.cols;
        return validRow && validCol;
    }

	@Override
	public MoveOutput moveElem(int x, int y, String dir) {
		return mapE[x][y].move(dir);
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
}
