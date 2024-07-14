package dataStructures;

public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {

    /**
     * Root of the given Tree to start to iterate
     */
    BSTNode<K, V> root;

    BSTNode<K, V> currentNode, top;
    /**
     * Stack of nodes that we will pop later
     */
    Stack<BSTNode<K, V>> nodes;

    public BSTKeyOrderIterator(BSTNode<K, V> root) {
        this.root = root;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return nodes.size() > 0;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        top = nodes.pop();

        if(top.getRight() != null){
            getMin(top.getRight());
        }

        return (Entry<K, V>) top;
    }

    private void getMin(BSTNode<K,V> node) {
        while (node != null){
            nodes.push(node);
            node = node.getLeft();
        }
    }

    @Override
    public void rewind() {
        currentNode = root;
        nodes = new StackInList<>();

        while(currentNode != null){
            nodes.push(currentNode);
            currentNode = currentNode.getLeft();
        }

    }
}
