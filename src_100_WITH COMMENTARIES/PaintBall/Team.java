package PaintBall;


import DataStructures.Iterator;

public interface Team {
	
	String getName();
    void conquerBunker(Bunker bunker);
    boolean isActive();
    void changeStatus();
    void addPlayer(Player player);
    void removePlayer(Player player);
    Iterator<Player> playerIterator();
    Iterator<Bunker> bunkerIterator();
    int numberBunkers();
    int numberPlayer();
    void removeBunker(Bunker bunker);
    void attack();
}
