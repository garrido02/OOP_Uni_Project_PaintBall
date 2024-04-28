package PaintBall;

public interface MapElement {
	
	char getChar();
	int getX();
	int getY();
	Team getTeam();
	Move move(String dir);
}
