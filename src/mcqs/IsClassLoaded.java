package mcqs;


public class IsClassLoaded {

    public static void main(String[] args) {
        Person p;
    }
}

class Person {

}

/**
 mcqs.Person is not loaded by ClassLoader since no instance is created
 Apart from that lots of classes are loaded like HashTable and Math that are required to run this class internally

 Can see using -verbose:class in VM Option in Run -> Edit Configs in Eclipse
 */
