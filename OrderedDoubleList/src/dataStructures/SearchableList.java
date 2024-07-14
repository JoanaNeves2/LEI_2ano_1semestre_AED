package dataStructures;

public interface SearchableList<E> extends List<E> {

    // finds the first element in the list equal to the one
    // in the parameter.
    // Otherwise, returns null.
    E findEquals(E element);


    /**
     * Removes the node which contains the specified element
     * @param element - Element contained by the node which will get removed
     * @return the element which got removed from the list
     */
    E removeElement(E element);

}
