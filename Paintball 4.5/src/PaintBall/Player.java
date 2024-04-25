package PaintBall;

public interface Player extends MapElement{
	MoveOutput move(String dir);
	int cost();
	String getType();

}
