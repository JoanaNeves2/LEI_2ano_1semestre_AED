package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * BSTKeyOrderIterator.
 *
 * @author Joana Simões Neves (65441) js.neves@campus.fct.unl.pt
 * @author Rui Xavier (65815) ra.xavier@campus.fct.unl.pt
 */
class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>>{
    private Stack<BSTNode<K, V>> stack;

    private final BSTNode<K, V> root;

    BSTKeyOrderIterator(BSTNode<K, V> root) {
        this.root = root;
        rewind();
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Entry<K, V> next() throws NoSuchElementException {
        if (stack.isEmpty()) throw new NoSuchElementException();
        return findNext().getEntry();
    }

    protected void findLowest(BSTNode<K, V> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeft();
        }
    }

    protected BSTNode<K, V> findNext() {
        BSTNode<K, V> node = stack.pop();
        if (node != null)
            findLowest(node.getRight());
        return node;
    }

    public void rewind() {
        stack = new StackInList<>();
        findLowest(root);
    }
}
