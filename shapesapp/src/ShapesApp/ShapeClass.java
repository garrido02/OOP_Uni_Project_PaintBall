package ShapesApp;

public abstract class ShapeClass implements Shape {
    private int x;
    private int y;
    private String ID;
    private String type;
    double area;

    public ShapeClass(String ID, int x, int y, String type){
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public String getShapeId() {
        return ID;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getArea() {
        return area;
    }
}
