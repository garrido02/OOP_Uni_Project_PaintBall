package PaintBall;

public class MoveClass implements Move{
    private Player player;
    private String dir;
    private int newX;
    private int newY;

    public MoveClass(Player player,  String dir){
        this.player = player;
        this.dir = dir;
    }

    @Override
    public void move() {

    }

    @Override
    public int getX() {
        return newX;
    }

    @Override
    public int getY() {
        return newY;
    }

    @Override
    public String playerType() {
        return null;
    }
}
