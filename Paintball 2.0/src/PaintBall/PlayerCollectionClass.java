package PaintBall;
import DataStructures.Array;
import DataStructures.ArrayClass;
import DataStructures.Iterator;

public class PlayerCollectionClass implements PlayerCollection {

    private static final int NOT_FOUND = -1;
    private Array<Player> players;

    public PlayerCollectionClass(){
        players = new ArrayClass<>();
    }
	@Override
	public void addPlayer(Player player) {
		players.insertLast(player);

	}
	
	public Player get(int i) {
		return players.get(i);
	}
	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}
	@Override
	public int getSize() {
		return players.size();
	}

}
