package ShapesApp;

public class rectangleClass extends ShapeClass{
    private static final String TYPE = "RECTANGLE";
    private int height;
    private int width;

    public rectangleClass(String ID, int x, int y, int height, int width) {
        super(ID, x, y, TYPE);
        this.height = height;
        this.width = width;
        super.area = height * width;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public String getShapeId() {
        return super.getShapeId();
    }
}
