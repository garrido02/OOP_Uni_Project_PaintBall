package ShapesApp;

public class rectangleClass extends ShapeClass{
    private int height;
    private int width;

    public rectangleClass(String ID, int x, int y, int height, int width) {
        super(ID, x, y);
        this.height = height;
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}
