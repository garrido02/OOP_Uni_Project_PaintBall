package PaintBall;
import DataStructures.*;

public interface BunkersCollection {
	
    void addBunker(Bunker bunker);
    boolean hasBunker(String name);
    void conquerBunker(Team team, String bunkerName);
    boolean isAbandoned(String bunkerName);
    boolean haveSpace(String bunkerName);
    boolean isBunkerFromTeam(String bunkerName,Team team);
    Iterator<Bunker> iterator();
    int getBunkersNr();
    boolean createPlayer(String playerType, String bunkerName);
}
