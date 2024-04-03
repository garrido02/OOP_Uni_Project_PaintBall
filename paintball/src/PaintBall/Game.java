package PaintBall;

public interface Game {
    boolean getStatus();
    String getCurrentTeam();
    boolean isValidPosition(int x, int y);
    boolean hasBunker(String name);
    boolean hasTeam(String name);
    boolean isAbandonedBunker(String name);
    void addBunker(int x, int y, String name, int treasury);
    void addTeam(String teamName, String bunkerName);
    void setCurrentTeam();
}
