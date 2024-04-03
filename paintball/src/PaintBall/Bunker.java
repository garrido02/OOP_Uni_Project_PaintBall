package PaintBall;

public interface Bunker {
    String getName();
    boolean isAbandoned();
    void changeOwner(String name);
    void incrementTreasury();
}
