package dataStructures;  

/**
 * Separate Chaining Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            //TODO: Original comentado para nao dar erro de compilacao.
             table[i] = new OrderedDoubleList<K,V>();
            //table[i] = null;
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            //TODO: left as an exercise.
        	//Original commented, to compile.
            this.rehash();
            return null;

        //TODO: Left as an exercise.
        return null;
    }

    private void rehash() {
        int capacity = maxSize * 2;
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        Dictionary<K, V>[] auxTable = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            auxTable[i] = new OrderedDoubleList<K,V>();

        Iterator<Entry<K,V>> it = iterator();
        maxSize = capacity;

        while(it.hasNext()){
            Entry<K,V> next = it.next();
            K key = next.getKey();
            int newHash = Math.abs(key.hashCode()) % auxTable.length;
            auxTable[newHash].insert(key, next.getValue());
        }

        table = auxTable;

    }

    @Override
    public V remove( K key )
    {
        //TODO: Left as an exercise.
        return null;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        //TODO: Left as an exercise.

        return null;
    } 
}































