package mcqs;

/**
 * @author gkabra
 * @since 250
 */

public class AutoCloseableExample implements AutoCloseable {
    public AutoCloseableExample() {
        System.out.println("AutoCloseableExample: Acquired");
    }

    public void performOperation() {
        System.out.println("AutoCloseableExample: Performing operation");
    }

    @Override
    public void close() {
        System.out.println("AutoCloseableExample: Closed");
    }
}

class Main {
    public static void main(String[] args) {
        try (AutoCloseableExample resource = new AutoCloseableExample()) {
            resource.performOperation();
        } // AutoCloseableExample is automatically closed at the end of the try block
    }
}

/*
As the try block exits, whether normally or due to an exception, the close() method of the CustomResourceClass is automatically called.
This demonstrates the automatic resource cleanup provided by Try-With-Resources.

Output:
    AutoCloseableExample: Acquired
    AutoCloseableExample: Performing operation
    AutoCloseableExample: Closed
 */
