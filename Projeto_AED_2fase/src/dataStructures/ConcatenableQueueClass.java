package dataStructures;

public class ConcatenableQueueClass<E> extends QueueInList<E> implements ConcatenableQueue<E>{
    @Override
    public void append(ConcatenableQueue<E> queue) {
        while(!queue.isEmpty())
        {
            this.enqueue(queue.dequeue());
        }
    }
}
