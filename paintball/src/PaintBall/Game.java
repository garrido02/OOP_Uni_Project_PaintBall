package PaintBall;

public interface Game {
    boolean getStatus();
    String getCurrentTeam();
    boolean isValidPosition(int x, int y);
    boolean hasBunker(String name);
    boolean hasTeam(String name);
    boolean isAbandonedBunker(String name);
}
