package mcqs;


// Define a functional interface
@FunctionalInterface
interface MyFunctionalInterface {
    void doSomething();
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Using a lambda expression to implement the abstract method of the functional interface
        MyFunctionalInterface functionalInterface = () -> System.out.println("Doing something...");

        // Calling the method defined in the functional interface
        functionalInterface.doSomething();

        // Using a method reference to implement the abstract method of the functional interface
        MyFunctionalInterface anotherFunctionalInterface = FunctionalInterfaceExample::doAnotherThing;

        // Calling the method defined in the functional interface
        anotherFunctionalInterface.doSomething();
    }

    // A method to be referenced by the functional interface
    public static void doAnotherThing() {
        System.out.println("Doing another thing...");
    }
}

/*
    If we do not use the @FunctionalInterface annotation, it won't affect the functionality of the interface itself.
    The @FunctionalInterface annotation is optional but recommended for clarity and documentation purposes.
    Without the @FunctionalInterface annotation, the interface MyFunctionalInterface would still be considered a functional interface as long as it contains only one abstract method.
    However, if you were to add more than one abstract method to the interface, the compiler would raise an error, as a functional interface can only have one abstract method.

    Output:
        Doing something...
        Doing another thing...
 */
