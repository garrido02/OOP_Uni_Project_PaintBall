package PaintBall;


import DataStructures.Iterator;

public interface Team {
	
	String getName();
    void conquerBunker(Bunker bunker);
    boolean isActive();
    void changeStatus();
    void addPlayer(Player player);
    Iterator<Player> playerIterator();
    Iterator<Bunker> bunkerIterator();
    int numberBunkers();
    int numberPlayer();
}
