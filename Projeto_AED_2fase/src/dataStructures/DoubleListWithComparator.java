package dataStructures;

public class DoubleListWithComparator<K extends Comparable<K>> extends DoubleList<K> {
    private static final long serialVersionUID = 0L;

    public DoubleListWithComparator() {
        super();
    }

    // Add an element to the list while maintaining order based on compareTo
    public void addWithComparator(K element) {
        DoubleListNode<K> newNode = new DoubleListNode<>(element, null, null);
        if (isEmpty() || element.compareTo(head.getElement()) > 0) {
            addFirst(element);
        } else if (element.compareTo(tail.getElement()) < 0) {
            addLast(element);
        } else {
            // Find the correct position based on compareTo
            DoubleListNode<K> current = head;
            while (current != null && element.compareTo(current.getElement()) < 0) {
                current = current.getNext();
            }
            // Insert the new node in the middle
            newNode.setPrevious(current.getPrevious());
            newNode.setNext(current);
            current.getPrevious().setNext(newNode);
            current.setPrevious(newNode);
            currentSize++;
        }
    }

}

