package ShapesApp;

public class ShapesCollectionApp implements ShapesCollection {
    private static final int DEFAULT_CAPACITY = 50;
    private Shape[] elements;
    private int size;

    public ShapesCollectionApp(){
        elements = new Shape[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean hasElem(String ID) {
        return false;
    }

    @Override
    public Shape getElement(String ID) {
        return null;
    }

    @Override
    public Iterator allShapesIterator() {
        return null;
    }

    @Override
    public Iterator allShapesIterator(String type) {
        return null;
    }

    @Override
    public void addElem(Shape elem) {
        elements[size++] = elem;
    }
}
