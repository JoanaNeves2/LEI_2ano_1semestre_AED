package dataStructures;


/**
 * SearchableList interface.
 *
 * @author Joana Simões Neves (65441) js.neves@campus.fct.unl.pt
 * @author Rui Xavier (65815) ra.xavier@campus.fct.unl.pt
 */
public interface SearchableList<E> extends List<E> {

    /**
     * Finds the first element in the list equal to the one
     * in the parameter.
     * If there are no elements equal to the one in the parameter
     * returns null.
     *
     * @param element - Element that we are looking for
     * @return the first element in the list equal to the one in the parameter.
     * If there are no elements equal to the one in the parameter returns null.
     */
    E findEquals(E element);


    /**
     * Removes the node which contains the specified element
     * @param element - Element contained by the node which will get removed
     * @return the element which got removed from the list
     */
    E removeElement(E element);

}
