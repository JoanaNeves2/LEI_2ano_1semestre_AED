package dataStructures;

public class SearchableDoubleList<E> extends DoubleList<E> implements SearchableList<E>{


    @Override
    public E findEquals(E element) {
        DoubleListNode<E> node = head;
        while (node != null && !node.getElement().equals(element)) {
            node = node.getNext();
        }
        if(node != null)
            return node.getElement();
        else
            return null;
    }

    @Override
    public E removeElement(E element){
        DoubleListNode<E> node = head;
        DoubleListNode<E> previous;
        DoubleListNode<E> next;
        while(node != null && !node.getElement().equals(element))
            node = node.getNext();

        assert(node != null);

        if(node == head)
            this.removeFirst();
        else if(node == tail)
            this.removeLast();
        else
            removeMiddleNode(node);

        return element;
    }
}
