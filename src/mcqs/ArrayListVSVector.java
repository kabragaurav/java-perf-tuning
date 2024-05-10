package mcqs;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ArrayListVSVector {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> vector = new Vector<>();

        // Creating multiple threads to add elements concurrently
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                arrayList.add(i);
                vector.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                arrayList.add(i);
                vector.add(i);
            }
        });

        // Start threads
        thread1.start();
        thread2.start();

        // Wait for threads to finish
        thread1.join();
        thread2.join();

        System.out.println("ArrayList size: " + arrayList.size());
        System.out.println("Vector size: " + vector.size());
    }
}


/*

Sample Output:
ArrayList size: 1959
Vector size: 2000
(Mostly size won't be same)

Similarly, HashMap is not thread safe but Hashtable is (having synchronized methods).
 */