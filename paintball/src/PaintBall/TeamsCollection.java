package PaintBall;
import DataStructures.*;

public interface TeamsCollection {
    boolean hasTeam(String name);
    void addTeam(String name, String bunker);
    String getCurrentTeam();
    void changeCurrentTeam();
    int getSize();
    Iterator<Team> iterator();
    int activeTeams();
}
