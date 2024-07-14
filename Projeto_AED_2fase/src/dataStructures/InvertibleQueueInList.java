package dataStructures;

import java.util.ListIterator;

public class InvertibleQueueInList<E> extends QueueInList<E> implements InvertibleQueue<E> {

    static final long serialVersionUID = 0L;
    boolean leftToRight = true;
    @Override
    public void invert(){
        leftToRight = !leftToRight;
    }

    @Override
    public void enqueue(E element){
        if(leftToRight)
            list.addLast(element);
        else {
            list.addFirst(element);
        }
    }

    @Override
    public E dequeue(){
        E element;
        if(leftToRight)
            element = list.removeFirst();
        else {
            element = list.removeLast();
        }
        return element;
    }
}
