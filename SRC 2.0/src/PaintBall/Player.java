package PaintBall;

public interface Player extends MapElement{
	Move move(String dir);
	int cost();
	String getType();
	boolean attack(MapElement element);
	int getPossibleMoves();
}
