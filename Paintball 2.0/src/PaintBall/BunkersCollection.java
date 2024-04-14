package PaintBall;
import DataStructures.*;

public interface BunkersCollection {
	
    void addBunker(Bunker bunker);
    boolean hasBunker(String name);
    void conquerBunker(Team team, String bunkerName);
    boolean isAbandoned(String bunkerName);
    Iterator<Bunker> iterator();
    int getBunkersNr();
}
