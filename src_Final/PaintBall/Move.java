package PaintBall;

public interface Move {
    int getX();
    int getY();
    String playerType();
    MoveOutput getOutput();
    Player getPlayer();
    boolean isSuccessful();
    

}
