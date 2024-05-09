package mcqs;

import java.util.ArrayList;

public class DefaultCollectionSize {
    public static void main(String[] args) {
        ArrayList<Integer> ls = new ArrayList<>();
        ls.ensureCapacity(500);
    }
}

/*
    How to ensure min capacity of arraylist.

    Theory:
    Trade off between memory and performance

    ArrayList: The default initial capacity is 10 elements. However, when the list grows beyond its current capacity, it automatically increases its capacity by approximately 50% to accommodate more elements.
    HashMap: The default initial capacity is 16 key-value pairs. Similar to ArrayList, when the number of elements exceeds the load factor (0.75 by default), the capacity is automatically increased, and the elements are rehashed into a larger capacity table.
    HashSet: Internally, HashSet uses a HashMap to store its elements. Therefore, its default initial capacity is also 16 elements, and it shares the same resizing behavior as HashMap.
    TreeSet: Internally, TreeSet uses a TreeMap to store its elements. Similar to HashMap, the default initial capacity of the TreeMap is 16 elements, and it dynamically adjusts its size as elements are added.
    LinkedList: Since LinkedList doesn't pre-allocate space like ArrayList, there's no default initial capacity.
    PriorityQueue: There's no default capacity since PriorityQueue is implemented using a heap.
 */
