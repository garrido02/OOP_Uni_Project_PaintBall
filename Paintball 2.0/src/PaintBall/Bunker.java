package PaintBall;
import DataStructures.*;

public interface Bunker {
    String getName();
    boolean isAbandoned();
    void changeOwner(String name);
    void incrementTreasury();
    String getOwner();
}
