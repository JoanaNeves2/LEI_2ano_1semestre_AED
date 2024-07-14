package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public class SepChainHashTableIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>> {
    int currentIndex;
    Iterator<Entry<K, V>> currentIt;
    SepChainHashTable<K, V> sp;

    @SuppressWarnings("unchecked")
    public SepChainHashTableIterator(SepChainHashTable sp) {
        this.sp = sp;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return currentIt.hasNext();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (this.currentIt == null) throw new NoSuchElementException();
        Entry<K, V> e = currentIt.next();
        findNextIt();
        return e;
    }

    @Override
    public void rewind() {
        currentIndex = 0;
        findNextIt();
    }

    private void findNextIt() {
        while (currentIt == null || (!currentIt.hasNext() && currentIndex < sp.table.length)) {
            currentIt = sp.table[currentIndex].iterator();
            currentIndex++;
        }
    }
}

