package PaintBall;

import DataStructures.Iterator;

public interface PlayerCollection {

	Player get(int i);
	void addPlayer(Player player);
	Iterator<Player> iterator();
	int getSize();

}
