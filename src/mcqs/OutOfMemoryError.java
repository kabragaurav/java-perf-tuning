package mcqs;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryError {
    public static void main(String[] args) {
        List<Memory> ls = new ArrayList<>();

        while (true) {
            ls.add(new Memory());
        }
    }
}

class Memory {

}

/*
Exception in thread "main" java.lang.mcqs.OutOfMemoryError: Java heap space
	at java.base/java.util.Arrays.copyOf(Arrays.java:3689)
	at java.base/java.util.ArrayList.grow(ArrayList.java:238)
	at java.base/java.util.ArrayList.grow(ArrayList.java:243)
	at java.base/java.util.ArrayList.add(ArrayList.java:486)
	at java.base/java.util.ArrayList.add(ArrayList.java:499)
	at Ok.main(Ok.java:14)
 */
