package ShapesApp;

public class IteratorClass implements Iterator{
    private Shape[] elements;
    private int size;
    private int nextIndex;
    private String type;

    public IteratorClass(Shape[] elements, int size){
        this.elements = elements;
        this.size = size;
        this.nextIndex = 0;
    }

    public IteratorClass(Shape[] elements, int size, String type){
        this.elements = elements;
        this.size = size;
        this.nextIndex = 0;
        this.type = type;
        searchNext();
    }

    public void searchNext(){
        while(hasNext() && !elements[nextIndex].getType().equals(type)){
            nextIndex++;
        }
    }

    @Override
    public boolean hasNext() {
        return nextIndex < size;
    }

    @Override
    public Shape next() {
        if (type == null) {
            return elements[nextIndex++];
        } else {
            Shape s = elements[nextIndex++];
            searchNext();
            return s;
        }
    }
}
