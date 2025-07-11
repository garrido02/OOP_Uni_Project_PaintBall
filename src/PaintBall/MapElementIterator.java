package PaintBall;
import DataStructures.Array;
import DataStructures.Iterator;

public class MapElementIterator implements Iterator<MapElement>{

	private static final int ZERO = 0;
	private MapElement[][] elems;
	private MapElement empty;
    private int current;
    private int currentRow;
    private int currentCol;
    private int rows;
    private int cols;
    private int size;
    private Team team;


    public MapElementIterator(MapElement[][] elems, Team team, MapElement empty){
        this.elems = elems;
        this.rows = elems[ZERO].length;
        this.cols = elems.length;
        this.size = rows*cols;
        this.team = team;    
        this.empty = empty;
        init();
        
    }
	
    @Override
    public boolean hasNext() {
        return current < size ;
    }
    
    private void init() {
    	this.current = ZERO;
        this.currentRow = ZERO;
        this.currentCol = ZERO;
    }
    
    private boolean hasNextRow() {
    	return currentRow < rows;
    }

    @Override
    public MapElement next(){
    	MapElement m = elems[currentCol++][currentRow];
    	if(!team.equals(m.getTeam()))
    		m = empty;
    	nextRow();
        return m;
    }

    private void nextRow(){
    	if(currentCol == this.cols && hasNextRow()) {
    		currentRow++;
        	currentCol = 0;
    	}	
    }
}
