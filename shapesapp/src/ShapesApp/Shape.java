package ShapesApp;

public interface Shape {
    String  getShapeId();
    int getX();
    int getY();
    void move(int x, int y);
    String getType();
    double getArea();
}
