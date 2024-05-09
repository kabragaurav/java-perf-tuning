package gc;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryError implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new OutOfMemoryError());
        t.start();
    }

    @Override
    public void run() {
        List<Memory> ls = new ArrayList<>();

        while (true) {
            ls.add(new Memory());
        }
    }
}

class Memory {

}