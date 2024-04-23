package PaintBall;
import DataStructures.*;

public class PlayersIterator implements Iterator<Player>{
    private Array<Player> elems;
    private int current;
    private Team teamFilter;


    public PlayersIterator(Array<Player> elems, Team teamFilter){
        this.elems = elems;
        this.current = 0;
        this.teamFilter = teamFilter;
        advance();
    }

    @Override
    public boolean hasNext() {
        return current < elems.size() ;
    }

    @Override
    public Player next() {
        Player t = elems.get(current++);
        advance();
        return t;
    }

    private void advance(){
        while (hasNext() && !elems.get(current).getTeam().equals(teamFilter)){
            current++;
        }
    }
}
