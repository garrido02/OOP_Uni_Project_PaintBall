package PaintBall;
import DataStructures.Array;
import DataStructures.ArrayClass;

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

}
