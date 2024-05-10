package mcqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExecutorServiceExample {
    public static void main(String[] args) {
        // Create a thread pool with fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            executor.submit(new Task(i));
        }

        // Shutdown the executor service
        executor.shutdown();
    }

    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Task " + taskId + " is being executed by thread: " + Thread.currentThread().getName());
        }
    }
}

/*
Sample Output:
Task 0 is being executed by thread: pool-1-thread-1
Task 2 is being executed by thread: pool-1-thread-3
Task 6 is being executed by thread: pool-1-thread-3
Task 4 is being executed by thread: pool-1-thread-5
Task 3 is being executed by thread: pool-1-thread-4
Task 1 is being executed by thread: pool-1-thread-2
Task 9 is being executed by thread: pool-1-thread-4
Task 8 is being executed by thread: pool-1-thread-5
Task 7 is being executed by thread: pool-1-thread-3
Task 5 is being executed by thread: pool-1-thread-1

As can be seen, Thread-2 executed only one task, so uneven distribution.

Theory:
A thread pool is a collection of worker threads that are ready to perform tasks.
It's a common pattern in concurrent programming where a pool of threads is created to execute tasks concurrently, for reusing threads instead of creating new ones for each task.
ExecutorService framework is used to manage thread pools and execute tasks asynchronously.
 */