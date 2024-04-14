package PaintBall;
import DataStructures.*;

public interface TeamsCollection {
    boolean hasTeam(String name);
    void addTeam(Team team);
    String getCurrentTeam();
    void changeCurrentTeam();
    int getSize();
    Iterator<Team> iterator();
    int activeTeams();
}
