package ShapesApp;

public class circleClass extends ShapeClass{
    private static final String TYPE = "CIRCLE";
    private int radius;

    public circleClass(String ID, int x, int y, int radius){
        super(ID, x, y, TYPE);
        this.radius = radius;
        super.area = (Math.PI * Math.pow(radius, 2));
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String getShapeId() {
        return super.getShapeId();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public int getX() {
        return super.getX();
    }
}
