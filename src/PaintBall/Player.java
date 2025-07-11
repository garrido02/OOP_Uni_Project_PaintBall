package PaintBall;
import DataStructures.Array;

public interface Player extends MapElement{
	Move move(String dir);
	int cost();
	String getType();
	boolean attack(MapElement element);
	int getPossibleMoves();
	Array<Coords> attackPattern(int rows, int cols);
	boolean isAlive();
	void eliminate();
}
