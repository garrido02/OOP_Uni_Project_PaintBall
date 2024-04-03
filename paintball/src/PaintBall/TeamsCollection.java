package PaintBall;

public interface TeamsCollection {
    boolean hasTeam(String name);
    void addTeam(String name, String bunker);
    String getCurrentTeam();
    void changeCurrentTeam();
}
