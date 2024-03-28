package ShapesApp;

/**
 * The ShapesApp.ShapesApp interface describes available operations over geometric shapes.
 */
public interface ShapesApp {
	
	/**
	 * Current supported geometric shapes.
	 */
	public final String CIRCLE = "CIRCLE";
	public final String RECTANGLE = "RECTANGLE";
	
	/**
	 * Checks if the provided type is a valid shape type.
	 * 
	 * @param type The type of shape to check.
	 * @return true if the provided type is a valid shape type, false otherwise.
	 * @pre type != null
	 */
	default boolean isValidType(String type) {
		return type.equals(CIRCLE) || type.equals(RECTANGLE);
	}
	
	/**
	 * Checks if the shape with the provided ID exists.
	 * 
	 * @param ID The identifier of the shape to check.
	 * @return true if there is a shape with the provided ID, false otherwise.
	 */
	boolean hasShape(String ID);
	
	/**
	 * Adds a circle to the application.
	 * 
	 * @param ID The identifier of the circle.
	 * @param x The x-coordinate of the circle's center.
	 * @param y The y-coordinate of the circle's center.
	 * @param radius The radius of the circle.
	 * @pre !has(ID)
	 */

	void addCircle(String ID, int x, int y, int radius);

	/**
	 * Adds a rectangle.
	 * 
	 * @param ID The identifier of the rectangle.
	 * @param x The x-coordinate of the top-left corner of the rectangle.
	 * @param y The y-coordinate of the top-left corner of the rectangle.
	 * @param height The height of the rectangle.
	 * @param width The width of the rectangle.
	 * @pre !has(ID)
	 */
	void addRectangle(String ID, int x, int y, int height, int width);

	/**
	 * Checks if the application is empty (contains no shapes).
	 * 
	 * @return true if the application is empty, false otherwise.
	 */
	boolean isEmpty();

	/**
	 * Moves a shape to the specified coordinates.
	 * 
	 * @param ID The identifier of the shape to move.
	 * @param x The new x-coordinate of the shape.
	 * @param y The new y-coordinate of the shape.
	 * @pre has(ID)
	 */
	void move(String ID, int x, int y);

	/**
	 * Finds the shape with the smallest area.
	 * 
	 * @return The shape with the smallest area.
	 * @pre !isEmpty()
	 */
	Shape smallestArea();
	
	/**
	 * Returns an iterator over all shapes in the application.
	 * 
	 * @return An iterator over all shapes in the application.
	 */
	Iterator allShapesIterator();

	/**
	 * Returns an iterator over shapes of the specified type.
	 * 
	 * @param type The type of shapes to iterate over (CIRCLE or RECTANGLE).
	 * @return An iterator over shapes of the specified type.
	 */
	Iterator allShapesIterator(String type);
}