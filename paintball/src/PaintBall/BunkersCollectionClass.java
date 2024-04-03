package PaintBall;

public class BunkersCollectionClass implements BunkersCollection{
    private static final int NOT_FOUND = -1;
    private Bunker[] bunkers;
    private int size;

    public BunkersCollectionClass(int bunkersNr){
        size = 0;
        bunkers = new Bunker[bunkersNr];
    }

    @Override
    public void addBunker(int x, int y, String name, int treasury) {
        bunkers[size++] = new BunkerClass(x, y, name, treasury);
    }

    @Override
    public boolean hasBunker(String name) {
        return findIndexOf(name) != NOT_FOUND;
    }

    @Override
    public void conquerBunker(String teamName, String bunkerName) {
        bunkers[findIndexOf(bunkerName)].changeOwner(teamName);
    }

    @Override
    public boolean isAbandoned(String bunkerName) {
        return bunkers[findIndexOf(bunkerName)].isAbandoned();
    }

    private int findIndexOf(String name){
        int i = 0;
        while(i < size && !bunkers[i].getName().equals(name)){
            i++;
        }
        if (i < size){
            return i;
        } else {
            return NOT_FOUND;
        }
    }
}
