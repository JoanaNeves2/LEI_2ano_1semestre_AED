package dataStructures;

public class EntryClass<K,V> implements Entry<K,V> {
    private K key;
    private V value;
    public EntryClass(K key,V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        // TODO Auto-generated method stub
        return key;
    }

    @Override
    public V getValue() {
        // TODO Auto-generated method stub
        return value;
    }

    @Override
    public void setKey(K newKey) {
        this.key = newKey;
    }

    public V setValue(V value) {
        V previousValue = this.value;
        this.value = value;
        return previousValue;
    }

}
