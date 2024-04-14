package PaintBall;
import DataStructures.Array;
import DataStructures.Iterator;

public class MapElementIterator implements Iterator<MapElement>{

	private MapElement[][] elems;
    private int current;
    private int currentRow;
    private int currentCol;
    private int rows;
    private int cols;
    private int size;
    private String team;


    public MapElementIterator(MapElement[][] elems, String team){
        this.elems = elems;
        this.current = 0;
        this.currentRow = 0;
        this.currentCol = 0;
        this.rows = elems[0].length;
        this.cols = elems.length;
        size = rows*cols;
        this.team = team;
        
    }
	
    @Override
    public boolean hasNext() {
        return current < size ;
    }
    
    private boolean hasNextRow() {
    	return currentRow < rows;
    }

    @Override
    public MapElement next() {
    	MapElement m = elems[currentRow][currentCol++];
    	//System.out.println(m.getChar());
    	if(!m.getTeamName().equals(team))
    		m = new EmptyElementClass();
    	if(currentCol == this.cols && hasNextRow())
    		nextRow();
        return m;
    }

    private void nextRow(){
    	currentRow++;
    	currentCol = 0;
    }

}
