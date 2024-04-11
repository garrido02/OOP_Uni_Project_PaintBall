package PaintBall;
import DataStructures.*;

public interface BunkersCollection {
    void addBunker(int x, int y, String name, int treasury);
    boolean hasBunker(String name);
    void conquerBunker(String teamName, String bunkerName);
    boolean isAbandoned(String bunkerName);
    Iterator<Bunker> iterator();
    int getBunkersNr();
}
