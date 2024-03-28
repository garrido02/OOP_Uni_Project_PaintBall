package ShapesApp;

/**
 * An iterator over a collection.
 */
public interface Iterator {



	/**
	 * Returns true if the iteration has more elements.
	 * @return true, if the iteration has more elements
	 */
	public boolean hasNext();

	/**
	 * Returns the next element in the iteration.
	 * @return the next element in the iteration
	 * @pre this.hasNext()
	 */
	public Shape next();
}