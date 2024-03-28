package ShapesApp;

/**
 * Interface ShapesApp.ShapesApp.ShapesCollection represents a collection of shapes.
 */
public interface ShapesCollection {
	
	/**
	 * Checks if the collection is empty.
	 * 
	 * @return true if the collection is empty, false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Checks if there is a shape with the provided identifier.
	 * 
	 * @param ID The identifier of the shape to check.
	 * @return true if there is a shape with the provided identifier, false otherwise.
	 * @pre ID != null
	 */
	boolean hasElem(String ID);
	
	/**
	 * Adds a shape to the collection.
	 * 
	 * @param elem The shape to add.
	 * @pre elem != null && this.hasElem(elem.getID())
	 */
	void addElem(Shape elem);
	
	/**
	 * Retrieves the shape with the provided identifier from the collection.
	 * 
	 * @param ID The identifier of the shape to retrieve.
	 * @return The shape with the provided identifier.
	 * @pre ID != null && this.hasElem(ID)
	 */
	Shape getElement(String ID);

	/**
	 * Returns an iterator over all shapes in the collection.
	 * 
	 * @return An iterator over all shapes in the collection.
	 */
	Iterator allShapesIterator();

	/**
	 * Returns an iterator over shapes of the specified type in the collection.
	 * 
	 * @param type The type of shapes to iterate over.
	 * @return An iterator over shapes with the specified type in the collection.
	 * @pre type != null && ShapesApp.ShapesApp.isValidType(type)
	 */
	Iterator allShapesIterator(String type);

	int findIndex(String ID);

}
