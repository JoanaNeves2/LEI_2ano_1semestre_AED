package dataStructures;

public class ConcatenableQueueInList<E>  extends QueueInList implements ConcatenableQueue{
    @Override
    public void append(ConcatenableQueue queue) {
        //método menos eficiente
        /*while(!queue.isEmpty()){
            this.enqueue(queue.dequeue());
        }*/

        //método mais eficiente
        this.append(queue);
    }
}
