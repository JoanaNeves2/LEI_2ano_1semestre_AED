package dataStructures;

/**
 * EntryClass
 *
 * @author Joana Simões Neves (65441) js.neves@campus.fct.unl.pt
 * @author Rui Xavier (65815) ra.xavier@campus.fct.unl.pt
 */
public class EntryClass<K,V> implements Entry<K,V> {

    protected K key;
    protected V value;

    public EntryClass(K key, V value){
        this.key = key;
        this.value = value;
    }

    //TODO: perguntar se posso mexer nas interfaces e por isto
    @Override
    public void setValue(V value){
        this.value = value;
    }

    @Override
    public void setKey(K key){
        this.key = key;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
}
