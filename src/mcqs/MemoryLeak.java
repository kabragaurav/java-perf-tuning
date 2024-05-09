package mcqs;

public class MemoryLeak {
    public static void main(String[] args) {
        new Animal();

        int[] a = new int[20];
        a = new int[50];
    }
}

class Animal {

}

/**
    Problem name is:
      memory leak at line 8 and 11
 */
