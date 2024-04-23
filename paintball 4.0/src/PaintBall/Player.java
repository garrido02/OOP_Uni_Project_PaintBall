package PaintBall;

public interface Player extends MapElement{
	void move();
	int cost();
	String getType();
}
