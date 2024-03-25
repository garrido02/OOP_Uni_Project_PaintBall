package ShapesApp;

public class ShapeClass implements Shape {
    protected int x;
    protected int y;
    protected String ID;

    public ShapeClass(String ID, int x, int y){
        this.ID = ID;
        this.x = x;
        this.y = y;
    }

    @Override
    public String getShapeId() {
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
