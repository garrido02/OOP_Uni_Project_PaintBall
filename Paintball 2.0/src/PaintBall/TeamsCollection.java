package PaintBall;
import DataStructures.*;

public interface TeamsCollection {
    boolean hasTeam(String name);
    void addTeam(Team team);
    Team getCurrentTeam();
    void changeCurrentTeam();
    int getSize();
    Iterator<Team> iterator();
    int activeTeams();
}
