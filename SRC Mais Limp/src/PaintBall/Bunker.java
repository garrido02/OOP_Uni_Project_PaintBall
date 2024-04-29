package PaintBall;
import DataStructures.*;

public interface Bunker extends MapElement{
    String getName();
    boolean isAbandoned();
    void changeTeam(Team t);
    void incrementTreasury();
    boolean createPlayer(String playerType);
    boolean isFree();
    int getTreasury();
    String getTeamName();
    void playerOut();
    void playerIn(Player player);
    Player getPlayer();

}
