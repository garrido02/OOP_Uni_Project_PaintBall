/**
 * @author Francisco Correia & Sérgio Garrido
 */


package PaintBall;


/**
 * Coords class responsible for implementing the Coords interface.
 */
public class CoordsClass implements Coords{
    private int x;
    private int y;

    /**
     * Constructor
     * @param x the horizontal position
     * @param y the vertical position
     */
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
