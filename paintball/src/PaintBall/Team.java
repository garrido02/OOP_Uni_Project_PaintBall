package PaintBall;

public interface Team {
    String getName();
    void conquerBunker(String name);
    boolean isActive();
    void changeStatus();
}
