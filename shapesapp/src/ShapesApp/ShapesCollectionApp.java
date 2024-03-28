package ShapesApp;

public class ShapesCollectionApp implements ShapesCollection {
    private static final int NOT_FOUND = -1;
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
        return findIndex(ID) != NOT_FOUND;
    }

    @Override
    public Shape getElement(String ID) {
        return elements[findIndex(ID)];
    }

    @Override
    public Iterator allShapesIterator() {
        return new IteratorClass(elements, size);
    }

    @Override
    public Iterator allShapesIterator(String type) {
        return new IteratorClass(elements, size, type);
    }

    @Override
    public void addElem(Shape elem) {
        elements[size++] = elem;
    }

    @Override
    public int findIndex(String ID) {
        int i = 0;
        while(i < size && !elements[i].getShapeId().equals(ID)){
            i++;
        }
        if (i < size){
            return i;
        } else {
            return NOT_FOUND;
        }
    }
}
