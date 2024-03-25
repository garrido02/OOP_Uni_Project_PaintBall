package ShapesApp;

public class circleClass extends ShapeClass {
    private int radius;

    public circleClass(String ID, int x, int y, int radius){
        super(ID, x, y);
        this.radius = radius;
    }

    public int getRadius(){
        return radius;
    }
}
