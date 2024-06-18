import java.util.Comparator;

public class SmarterPQ<V, K>
{
    private Entry<V, K>[] a;
    private int lastEntry = 0;
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
        return lastEntry;
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

    private int leftChild(int position) {
        return position * 2 + 1;
    }

    private int rightChild(int position) {
        return position * 2 + 2;
    }

    private void swap(int i, int j) {
        Entry<V, K> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void upHeap(int position) {
        while(position != 0 && comparator.compare(a[parent(position)].getKey(), a[position].getKey()) * state < 0) {
            swap(position, parent(position));
            position = parent(position);
        }
    }

    private void downHeap(int position) {
        while(leftChild(position) < a.length && leftChild(position) < lastEntry) {

            int downHeapPosition = 0;

            if(comparator.compare(a[position].getKey(), a[leftChild(position)].getKey()) * state < 0) {
                downHeapPosition = leftChild(position);
            }
            
            if(rightChild(position) < a.length && rightChild(position) < lastEntry && comparator.compare(a[position].getKey(), a[rightChild(position)].getKey()) * state < 0) {
                if(downHeapPosition == 0) downHeapPosition = rightChild(position);

                else if(comparator.compare(a[downHeapPosition].getKey(), a[rightChild(position)].getKey()) * state < 0) {
                    downHeapPosition = rightChild(position);
                }
            }

            if(downHeapPosition != 0) {
                swap(position, downHeapPosition);
                position = downHeapPosition;
            }

            else break;
        }
    }

    public void insert(V value, K key) {
        if(lastEntry == a.length) expand();

        a[lastEntry] = new Entry<V, K>(value, key);

        upHeap(lastEntry);

        lastEntry++;
    }

    public Entry<V, K> removeTop() {
        Entry<V, K> returnEntry = a[0];
        a[0] = a[lastEntry - 1];
        a[lastEntry - 1] = null;
        lastEntry--;

        if(lastEntry > 0) {
            downHeap(0);
        }

        return returnEntry;
    }

    public void printArray() {
        for(int i = 0; i < a.length; i++) {

            if(a[i] == null) break;
            System.out.print("pos " + i + ": " + a[i].getValue() + " : " + a[i].getKey() + " | ");
        }
    }

    public void toggle() {
        state *= -1;

        for (int i = (lastEntry / 2) - 1; i >= 0; i--) {
            downHeap(i);
        }
    }

    public Entry<V, K> top() {
        return a[0];
    }

    public boolean isEmpty() {
        return (lastEntry == 0) ? true : false;
    }

    public int size() {
        return lastEntry;
    }

    public String state() {
        return (state > 0) ? "max" : "min";
        }
}