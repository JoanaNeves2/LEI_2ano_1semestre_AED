package dataStructures;

public class InvertibleQueueInList<E> extends QueueInList<E> implements InvertibleQueue<E>{

   /* @Override
    public void invert() {
        List<E> aux = new DoubleList<>();
        while(!this.isEmpty()){
            aux.addLast(this.dequeue());
        }

        while(!aux.isEmpty()) {
            this.enqueue(aux.removeLast());
        }

        //ou
        //while(this.isEmpty()){
        //    aux.addFirst(this.dequeue());
        //}
        //this.list= aux;

    }
    */

    boolean leftToRight = true;

    @Override
    public void invert() { //O(2)
        leftToRight = !leftToRight;
    }

    @Override
    public void enqueue(E elem){ //O(1)
        if(leftToRight)
            list.addLast(elem);
        else
            list.addFirst(elem);
    }

    @Override
    public E dequeue(){
        E elem = null;
        if(!list.isEmpty()) {
            if (leftToRight)
                elem = list.removeFirst();
            else
                elem = list.removeLast();
        }
        return elem;
    }

}
