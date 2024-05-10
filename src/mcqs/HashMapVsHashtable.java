package mcqs;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class HashMapVsHashtable {
    public static void main(String[] args) throws InterruptedException {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> hashtable = new Hashtable<>();

        // Creating multiple threads to add elements concurrently
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                hashMap.put("Key" + i, i);
                hashtable.put("Key" + i, i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                hashMap.put("Key" + i, i);
                hashtable.put("Key" + i, i);
            }
        });

        // Start threads
        thread1.start();
        thread2.start();

        // Wait for threads to finish
        thread1.join();
        thread2.join();

        System.out.println("HashMap size: " + hashMap.size());
        System.out.println("Hashtable size: " + hashtable.size());
    }
}

/*
Sample output:
HashMap size: 1999
Hashtable size: 2000
(Mostly size won't be same)

Similarly, ArrayList is not thread safe but Vector is (having synchronized methods).
 */
