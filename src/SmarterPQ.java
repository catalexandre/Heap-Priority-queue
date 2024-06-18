import java.util.Comparator;

public class SmarterPQ<V, K>
{
    private Entry<V, K>[] a;
    private int position = 0;
    private byte state = 1;
    private Comparator comparator;

    public SmarterPQ(Comparator comparator) {
        a = (Entry<V, K>[])(new Entry[1]);
        this.comparator = comparator;
    }

    public SmarterPQ(Comparator comparator, int capacity) {
        a = (Entry<V, K>[])(new Entry[capacity]);
        this.comparator = comparator;
    }

    public int getSize() {
        return position;
    }

    private void expand() {
        Entry<V, K>[] newPQ = (Entry<V, K>[])(new Entry[a.length * 2]);

        for(int i = 0; i < getSize(); i++) {
            newPQ[i] = a[i];
        }

        a = newPQ;
    }

    private int parent(int position) {
        return (position % 2 == 0) ? (position - 2) / 2 : (position - 1) / 2;
    }

    private void swap(int i, int j) {
        Entry<V, K> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void insert(V value, K key) {
        if(position == a.length) expand();

        a[position] = new Entry<V, K>(value, key);

        int upHeapPosition = position;

        while(upHeapPosition != 0 && comparator.compare(a[parent(upHeapPosition)].getKey(), a[upHeapPosition].getKey()) * state >= 0) {
            swap(upHeapPosition, parent(upHeapPosition));
            upHeapPosition = parent(upHeapPosition);
        }

        position++;
    }

    public byte state() {
        return state;
    }

    public void printArray() {
        for(int i = 0; i < a.length; i++) {

            if(a[i] == null) break;
            System.out.print("pos " + i + ": " + a[i].getValue() + " : " + a[i].getKey() + " | ");
        }
    }
}