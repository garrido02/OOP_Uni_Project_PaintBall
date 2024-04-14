package PaintBall;
import DataStructures.*;

public interface Bunker {
    String getName();
    boolean isAbandoned();
    void changeTeam(Team t);
    void incrementTreasury();
    Team getTeam();

}
