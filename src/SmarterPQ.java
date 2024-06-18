import java.util.Comparator;

public class SmarterPQ<V, K>
{
    //the array that holds the entries
    private Entry<V, K>[] a;

    //holds the position of the next entry to be inserted in the heap
    private int nextEntry = 0;

    //state 1 represents max, -1 min
    //the logic is that multiplying the result of the comparator by state inverse the input or not depending on the need
    private byte state;

    //variable to hold the priority queues comparator
    private Comparator comparator;

    //constructor with comparator and size initialized to one
    public SmarterPQ(Comparator comparator) {
        a = (Entry<V, K>[])(new Entry[1]);
        this.comparator = comparator;
        this.state = 1;
    }

    //constructor with comparator and size initialized to capacity
    public SmarterPQ(Comparator comparator, int capacity) {
        a = (Entry<V, K>[])(new Entry[capacity]);
        this.comparator = comparator;
        this.state = 1;
    }

    //constructor with comparator, state, and size initialized to one
    public SmarterPQ(Comparator comparator, byte state) {
        a = (Entry<V, K>[])(new Entry[1]);
        this.comparator = comparator;
        this.state = state;
    }

    //constructor with comparator, state, and size initialized to capacity
    public SmarterPQ(Comparator comparator, int capacity, byte state) {
        a = (Entry<V, K>[])(new Entry[capacity]);
        this.comparator = comparator;
        this.state = state;
    }

    //returns size of queue
    public int getSize() {
        return nextEntry;
    }

    //expands the array by doubling the size, making the amortized time complexity O(1)
    private void expand() {
        System.out.println("expanded");
        Entry<V, K>[] newPQ = (Entry<V, K>[])(new Entry[a.length * 2]);

        for(int i = 0; i < getSize(); i++) {
            newPQ[i] = a[i];
        }

        a = newPQ;
    }

    //used to find the parent of a node
    private int parent(int position) {
        return (position % 2 == 0) ? (position - 2) / 2 : (position - 1) / 2;
    }

    //used to find the left child of a node
    private int leftChild(int position) {
        return position * 2 + 1;
    }

    //used to find the right child of a node
    private int rightChild(int position) {
        return position * 2 + 2;
    }

    //swaps two entries
    //used in downHeap and upHeap
    private void swap(int i, int j) {
        Entry<V, K> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //upheap function to restore order in the heap
    private void upHeap(int position) {
        //will always swap if the comparison result shows necessity
        while(position != 0 && comparator.compare(a[parent(position)].getKey(), a[position].getKey()) * state < 0) {
            swap(position, parent(position));
            position = parent(position);
        }
    }

    //downHeap is used to restore correct hierarchy
    private void downHeap(int position) {

        //checks if the parent can be swapped with a child
        while(leftChild(position) < a.length && leftChild(position) < nextEntry) {

            int downHeapPosition = 0;

            //check which child the parent should be swapped with, if any
            if(comparator.compare(a[position].getKey(), a[leftChild(position)].getKey()) * state < 0) {
                downHeapPosition = leftChild(position);
            }
            
            if(rightChild(position) < a.length && rightChild(position) < nextEntry && comparator.compare(a[position].getKey(), a[rightChild(position)].getKey()) * state < 0) {
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

    //insert an entry in the queue
    public void insert(V value, K key) {
        if(nextEntry == a.length) expand();

        a[nextEntry] = new Entry<V, K>(value, key);

        upHeap(nextEntry);

        nextEntry++;
    }

    //change the state of the queue from max to min and vice versa
    public void toggle() {
        state *= -1;

        //rebuilds the heap in place
        for (int i = (nextEntry / 2) - 1; i >= 0; i--) { //(nextEntry / 2) means we ignore downHeap of leaves
            downHeap(i);
        }
    }

    //replaces the key of an entry
    public K replaceKey(Entry<V, K> entry, K key) {
        Entry<V, K> returnEntry = null;

        for(int i = 0; i < nextEntry; i++) {
            if(a[i] == entry) {
                returnEntry = new Entry<V, K>(entry);
                a[i].setKey(key);;

                int keyDifference = comparator.compare(returnEntry.getKey(), key) * state;

                if(keyDifference < 0) {
                    upHeap(i);
                }

                else if(keyDifference > 0) {
                    downHeap(i);
                }

                break;
            }
        }

        return (returnEntry != null) ? returnEntry.getKey() : null;
    }

    //replace the value of an entry
    public V replaceValue(Entry<V, K> entry, V value) {
        Entry<V, K> returnEntry = null;

        for(int i = 0; i < nextEntry; i++) {
            if(a[i] == entry) {
                returnEntry = new Entry<V, K>(entry);
                a[i].setValue(value);;

                break;
            }
        }

        return (returnEntry != null) ? returnEntry.getValue() : null;
    }

    //remove an entry
    public Entry<V, K> remove(Entry<V, K> entry) {
        Entry<V, K> returnEntry = null;

        for(int i = 0; i < nextEntry; i++) {
            if(a[i] == entry) {
                returnEntry = new Entry<V, K>(entry);
                a[i] = a[nextEntry - 1];
                downHeap(i);
                nextEntry--;

                break;
            }
        }

        return (returnEntry != null) ? returnEntry : null;
    }

    //remove the top entry, or entry at position zero in the queue
    public Entry<V, K> removeTop() {
        return remove(top());
    }

    //return the top entry
    public Entry<V, K> top() {
        return a[0];
    }

    //checks if the queue is empty
    public boolean isEmpty() {
        return (nextEntry == 0) ? true : false;
    }

    //returns the state of the queue as a string
    public String state() {
        return (state > 0) ? "max" : "min";
    }
}