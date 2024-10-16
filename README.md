# Java Performance Tuning

This repo holds:
- MCQs for Java deep dives
- <a href="./src/multithreading/VISUAL_VM.md">Thread Profiling Using VisualVM</a>
  <img src="./assets/images/visual_vm_both_threads.png">
- <a href="./assets/pdfs/JavaPerformanceTuning.pdf">GC and Heap PDF</a>

=======

* JMX : JMX (Java Management Extensions) is a technology used for monitoring and managing Java applications, facilitating tasks like performance monitoring, configuration management, and remote control through standardized interfaces and components.
* RMI: RMI (Remote Method Invocation) is a Java API allowing communication between Java objects in different JVMs over a network, enabling distributed application development.
* javaw: `w` stands for windowed. It is a tool used to run Java applications without showing a command prompt window. It's handy for applications with graphical interfaces or background services that don't need a visible console.

=======

## Memory Model
<img src="./assets/images/memory_model.png">

<img src="./assets/images/jre.png">

- Using `new` memory is allocated on Heap, but refs exist on Stack
- String pool is part of Java heap
- Stack stores intermediate results for methods
- Each thread has its own thread. Especially take care on servers, since servers are intended to be 24x7
- Each thread has separate set of CPU registers and execution state but can share shared code and heap
- Method Area stores code (ctor, variables etc.)
- JNI (Java Native Interface) facilitates the interaction between Java and native code, the Native Method Area is where information about native methods is stored within the JVM
- Program counter is used by JVM the next instruction to be executed

# Portability vs Native Code Generation
The JVM provides runtime services such as memory management, garbage collection, and security checks during program execution & hardware abstraction and hence called virtual machine.
Compiled C++ programs produce standalone executables that do not rely on a separate runtime environment. They interact directly with the underlying operating system and hardware without an intermediary layer like a virtual machine.

# GC
Need to prevent `segmentation fault` and `memory leak` issues

`System.gc() == Runtime.getRuntime().gc()`
(Above calls induce full GC, which may suspend threads, use `-XX:+DisableExplicitGC` and <a href="https://medium.com/@marko.ullgren/system-gc-considered-harmful-fe6c44078d2e">prefer never to call GC explicitly</a>)

<img src="./assets/images/heap.png">

#
- `S0`, `S1` = servival memory and `Perm` = permanent memory and `Xms` = X min, `Xmx` = X max
#### From Java8, perm is now called `Metaspace` and now not part of heap and contains app metadata
- Memory pool used for immutable objects can belong to heap or perm memory
- New objects always created in nursery (aka young gen) (Eden, S0, S1)
  <img src="./assets/images/heap_gc.png">
- How GC happens <br/>
<img src="./assets/images/gc1.png">
<img src="./assets/images/gc2.png">
<img src="./assets/images/gc3.png">
<img src="./assets/images/gc4.png">
<img src="./assets/images/gc5.png">
<img src="./assets/images/gc6.png">

Objects are copied to S0 from Eden so that fragments are not created and Eden can start fresh altogether.

#
- If an object survives multiple GC cycles, it is long living and hence promoted to old gen heap area to avoid moving from Eden -> S0 -> S1
<br/>
- When orange line goes up/blue line goes down, that means GC is happening:
<img src="./assets/images/visual_vm_monitor_heap.png">
- <img src="./assets/images/visual_vm_monitor_heap2.png">

### Memory Sizes and Flags
Using CLI, pass as `java -Xms2560m MyCls` (sets the initial heap size to 2560 megabytes)
<img src="./assets/images/mem_sz1.png">
<img src="./assets/images/mem_sz2.png">
<img src="./assets/images/mem_sz3.png">

### Logs
Can heapdump in VisualVM to store heap info at a moment.
<img src="./assets/images/gc_logs.png">

### How GC Works?
- There is no class unloading mechanism. So a class once loads remains in method area. And so do static variables. But if we create our own class loader, once that is removed, loaded classes are GCed
- JVM does not load class until needed. `import` does not mean loading, it is just for JVM to know location of class
- 4 Types of GC roots: local vars, static vars, JNI refs and active Java threads
- Once mark and sweep is done, compaction is done to remove fragments

### Types of GC
HotSpot JVM provides:
1. Serial GC: does mark-sweep and compaction. Only one thread
2. Parallel GC: Used to be JVM default till Java8. Uses multiple threads `-XX:ParallelGCThreads=<N>`, select `-XX:+UseParallelGC`. It uses Stop-the-World strategy
3. Concurrent Mark and Sweep (CMS): Does not use stop the world strategy but stops threads, if changes happen in heap
4. G1: Now default GC from Java9. Designed for multiprocessor, large memory systems. Divides heap in equal size spaces and firstly reclaims spaces which are mostly empty. It suspends threads only briefly

### How to make object ready for GC
1. Create local var
2. Nullify ref
3. Reassign ref
4. Create anonymous ref

# Loading Activities
### How class is loaded
- Loading means reading from disk and storing binary data in method area
- JVM stores info like fully-qualified name of class and immediate parent class, ctor, method, variable, modifiers infos, whether `.class` is class, interface or enum
- For each loaded class, an object is created in heap <br/>
  <img src="./assets/images/cls_load.png">
- `ClassLoader` is abstract class. From Oracle documentation:
    <img src="./assets/images/load_cls_byte_arr.png">

### Linking
- JVM checks sanity of bytecode (like formatting)
- Replaces symbolic names (variable names) with actual memory refs
- Static variables are initialized from Parent -> Child order

### Types of loaders
1. Bootstrap - Written in C/C++ (Native). Loads `rt.jar` classes like `java.lang`, `java.util`, `java.io` and `java.net` (rt = runtime)
2. Extension - loads extensions (additional APIs, libraries, and tools that are not part of the standard Java distribution but are commonly used in Java development) from `ext` folder
3. Application or System

Parent to Child relationship: `Bootstrap -> Extension -> Application/System` <br/>
Delegation happens to parent and if parent is not able to load, it delegates back to child (as shown below)
Otherwise `ClassNotFoundException` is thrown:
<img src="./assets/images/cls_loading_mech.png">

# Multithreading

`ThreadLocal` in Java provides thread-local variables. These are variables that are only visible and accessible to the thread that sets them. Each thread has its own, independently initialized copy of the variable.

# Tips
1. Do not create object unless needed. And create object just before its usage
2. Make methods static which do not use instance data, so that they can be called as `Cls.method()` and no need to create obj of class (Object creation is heavy process, need to go up hierarchy to call all ctors)
3. Use right GC for your app - can have stop-the-world event? Want to use multiprocessor?
4. For one time use, create anonymous objects
   
```
public class AnonymousObjectExample {
    public static void main(String[] args) {
        // Creating an anonymous object of the Person class
        new Person().introduce(); // Anonymous object calling the method directly
    }
}

class Person {
    public void introduce() {
        System.out.println("Hi, I am Gaurav's anonymous person!");
    }
}
```

5. Release variable when no longer needed by setting to `null`
6. Use try-with-resource `AutoCloseable` interface resource management utility. So no need to close resources by making `finally` block
7. Static fields are GC roots, which means they are never garbage-collected. So, never use mutable static fields — use only constants.
8. Use helper clases - At times it is possible to have a class with lot of static variables and methods. Split these classes into smaller classes so that only those
   classes which are being used are loaded in the memory
9. Use `StringBuilder` if possible. Also avoid wrapper and BigInteger (or similar) classes if possible
10. Since recursion can create lot of local vars, do not use recursion, if loop is feasible
11. Optimize SQL queries - use indexes, use conditional retrievals, try avoiding too many joins, use joins over subqueries
12. If multiple checks on String validations, instead of if-else, use regex
13. JPA vs JDBC - JDBC is lower layer to manually interact with DB. JPA's implementation like Hibernate provides more OO way and caching, lazy loading and auto schema generation capabilities.
