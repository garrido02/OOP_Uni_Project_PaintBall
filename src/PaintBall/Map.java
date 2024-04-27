package PaintBall;
import DataStructures.*;

public interface Map {
    int getWidth();
    int getHeight();
    boolean isValidPosition(int x, int y);
    boolean isInside(int x, int y);
    void addElement(MapElement e, int x, int y);
    Iterator<MapElement> iterator(Team team);
    Move moveElem(int x, int y, Move newPos);

    public boolean nextCellOccupied(int x, int y, String dir);
}
