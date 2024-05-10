package mcqs;

import java.sql.DriverManager;
import java.util.ArrayList;

public class ClassLoaderExample {
    public static void main(String[] args) {
        System.out.println("Loader for ArrayList " + ArrayList.class.getClassLoader());
        System.out.println("Loader for DriverManager class: " + DriverManager.class.getClassLoader());
        System.out.println("Loader for String class: " + String.class.getClassLoader());
    }
}

/*
Output:
Loader for ArrayList null
Loader for DriverManager class: jdk.internal.loader.ClassLoaders$PlatformClassLoader@58fbd02e
Loader for String class: null

Explanation:
ArrayList and String are part of the core Java runtime library (java.util package),
and classes from the core Java runtime library (rt.jar) are loaded by the bootstrap class loader.
Since the bootstrap class loader is implemented in native code, its reference is represented as null in Java.
 */
