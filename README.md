My own implementation of a heap priority queue done for a university assignment

## Tight Big-O complexities
### toggle()
O(n log n)
toggle() rebuilds the entire heap by doing downHeap() operations for every node that is internal, starting from those at the bottom, and there are n nodes.

### remove(e)
O(log n)
remove() needs to perform downHeap() for the node that is placed at the position of the one that was removed, and downHeap() runs in log n time.

### replaceKey(e, k)
O(log n)
replaceKey() needs to perform downHeap() for the new key and downHeap() runs in log n time.

### replaceValue(e, v)
O(1)
Since the entry is given, this operation is done in constant time.