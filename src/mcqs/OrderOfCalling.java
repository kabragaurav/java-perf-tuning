package mcqs;

/**
 * @author gkabra
 * @since 250
 */

public class OrderOfCalling {

    private static String name = "Gaurav";

    static {
        name = "Kabra";
        System.out.println("Static initializer block1");
    }

    static {
        name = "GK";
        System.out.println("Static initializer block2");
    }

    {
        System.out.println("Instance initializer block1");
    }

    {
        System.out.println("Instance initializer block2");
    }

    public OrderOfCalling() {
        System.out.println("Default Constructor");
    }

    public static void main(String[] args) {
        System.out.println(name);
        System.out.println("\nCreating first instance:");
        OrderOfCalling obj1 = new OrderOfCalling();

        System.out.println("\nCreating second instance:");
        OrderOfCalling obj2 = new OrderOfCalling();
    }
}

/*
    Static initializer block1
    Static initializer block2
    GK

    Creating first instance:
    Instance initializer block1
    Instance initializer block2
    Default Constructor

    Creating second instance:
    Instance initializer block1
    Instance initializer block2
    Default Constructor

    ---------------------------

    When to use static init block?
        If initialization is required at class loading only once when the class is loaded, such as setting up static fields or loading resources

    When to use instance init block?
         When you need to initialize instance variables with complex logic or need to perform multiple steps during instance creation, an instance initializer block provides a way to organize this logic.
 */
