package PaintBall;
import DataStructures.*;

public class BunkersCollectionClass implements BunkersCollection{
    private static final int NOT_FOUND = -1;
    private Array<Bunker> bunkers;

    public BunkersCollectionClass(int bunkersNr){
        bunkers = new ArrayClass<>(bunkersNr);
    }

    @Override
    public int getBunkersNr() {
        return bunkers.size();
    }

    @Override
    public Iterator<Bunker> iterator() {
        return bunkers.iterator();
    }

    @Override
    public void addBunker(Bunker bunker) {
        bunkers.insertLast(bunker);
    }

    @Override
    public boolean hasBunker(String name) {
        return findIndexOf(name) != NOT_FOUND;
    }

    @Override
    public void conquerBunker(Team team, String bunkerName) {
        bunkers.get(findIndexOf(bunkerName)).changeTeam(team);
    }

    @Override
    public boolean isAbandoned(String bunkerName) {
        return bunkers.get(findIndexOf(bunkerName)).isAbandoned();
    }

    private int findIndexOf(String name){
        int i = 0;
        Iterator<Bunker> ite = bunkers.iterator();
        while (ite.hasNext()){
            if (ite.next().getName().equals(name)){
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }
}
