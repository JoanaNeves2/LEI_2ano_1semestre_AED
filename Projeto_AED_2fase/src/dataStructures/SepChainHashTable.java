package dataStructures;

/**
 * Separate Chaining Hash table implementation
 *
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 * @author AED  Team
 * @version 1.0
 */

public class SepChainHashTable<K extends Comparable<K>, V>
        extends HashTable<K, V> {
    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * The array of dictionaries.
     */
    protected Dictionary<K, V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     *
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];
        for (int i = 0; i < arraySize; i++) {
            //TODO: Original comentado para nao dar erro de compilacao. DONE
            table[i] = new OrderedDoubleList<K, V>();
        }
        maxSize = capacity;
        currentSize = 0;
    }


    public SepChainHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns the hash value of the specified key.
     *
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public V find(K key) {
        return table[this.hash(key)].find(key);
    }

    @Override
    public V insert(K key, V value) {
        //TODO: left as an exercise. DONE
        //Original commented, to compile.
        if (this.isFull())
            this.rehash();

        Dictionary<K, V> temp = table[hash(key)];

        V oldValue = temp.insert(key, value);

        if (oldValue == null)
            currentSize++;

        return oldValue;
    }

    @Override
    public V remove(K key) {
        //TODO: Left as an exercise. DONE
        Dictionary<K, V> temp = table[hash(key)];

        V value = temp.remove(key);

        if (value != null)
            currentSize--;

        return value;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        //TODO: Left as an exercise. DONE

        return new SepChainHashTableIterator<>(this);
    }

    @SuppressWarnings("unchecked")
    protected void rehash() {
        Dictionary<K, V>[] oldTable = this.table;

        table = new Dictionary[nextPrime(2 * table.length)];

        for (int i = 0; i < table.length; i++) {
            table[i] = new OrderedDoubleList<>();
        }

        this.maxSize = table.length;
        this.currentSize = 0;

        for (Dictionary<K, V> kvDictionary : oldTable) {
            Iterator<Entry<K, V>> it = kvDictionary.iterator();

            while (it.hasNext()) {
                Entry<K, V> e = it.next();
                this.insert(e.getKey(), e.getValue());
            }
        }
    }
}
































