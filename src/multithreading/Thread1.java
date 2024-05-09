package multithreading;

/**
 * @author gkabra
 * @since 250
 */

public class Thread1 implements Runnable {
    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println("Thread1 : " + i);
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
