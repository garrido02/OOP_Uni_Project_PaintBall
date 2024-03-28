package ShapesApp;

public class ShapesAppClass implements ShapesApp{
    private ShapesCollection shapes;

    public ShapesAppClass(){
        shapes = new ShapesCollectionApp();
    }

    @Override
    public boolean hasShape(String ID) {
        return shapes.hasElem(ID);
    }

    @Override
    public void addCircle(String ID, int x, int y, int radius) {
        Shape circle = new circleClass(ID, x, y, radius);
        shapes.addElem(circle);
    }

    @Override
    public void addRectangle(String ID, int x, int y, int height, int width) {
        Shape rectangle = new rectangleClass(ID, x, y, height, width);
        shapes.addElem(rectangle);
    }

    @Override
    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    @Override
    public void move(String ID, int x, int y) {
        Shape shape = shapes.getElement(ID);
        shape.move(x, y);
    }

    @Override
    public Shape smallestArea() {
        Iterator ite = allShapesIterator();
        Shape minAreaShape = ite.next();
        while(ite.hasNext()){
            Shape currentShape = ite.next();
            if (currentShape.getArea() <= minAreaShape.getArea()){
                minAreaShape = currentShape;
            }
        }
        return minAreaShape;
    }

    @Override
    public Iterator allShapesIterator() {
        return shapes.allShapesIterator();
    }

    @Override
    public Iterator allShapesIterator(String type) {
        return shapes.allShapesIterator(type);
    }
}
