package PaintBall;


import DataStructures.Iterator;

public interface Team {
	
	String getName();
    void conquerBunker(String name);
    boolean isActive();
    void changeStatus();
    void addPlayer(Player player);
    Iterator<Player> iterator();
    int numberPlayer();
}
