public class Entry<V, K> {
    private V value;
    private K key;

    public Entry(V value, K key) {
        this.value = value;
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

}
