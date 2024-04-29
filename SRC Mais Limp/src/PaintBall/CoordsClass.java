package PaintBall;

public class CoordsClass implements Coords{
    private int x;
    private int y;

    public CoordsClass(int x, int y){
       this.x = x;
       this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
