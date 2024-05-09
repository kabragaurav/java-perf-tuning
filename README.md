# Java Performance Tuning
This repo holds:
- MCQs for Java deep dives
- <a href="./src/multithreading/VISUAL_VM.md">Thread Profiling Using VirtualVM</a>
  <img src="./assets/images/visual_vm_both_threads.png">

## Memory Model
<img src="./assets/images/memory_model.png">

# 
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