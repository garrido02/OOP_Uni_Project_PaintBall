package PaintBall;
import DataStructures.*;

public class ActiveTeamsIterator implements Iterator<Team>{
    private Array<Team> elems;
    private int current;


    public ActiveTeamsIterator(Array<Team> elems){
        this.elems = elems;
        this.current = 0;
        advance();
    }

    @Override
    public boolean hasNext() {
        return current < elems.size() ;
    }

    @Override
    public Team next() {
        Team t = elems.get(current++);
        advance();
        return t;
    }

    private void advance(){
        while (hasNext() && !elems.get(current).isActive()){
            current++;
        }
    }
}
