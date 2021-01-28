package com.tts.threads;

import java.util.ArrayList;
import java.util.List;

// liveness - describes a concurrent applications ability to exe in a timely manner
// deadlock - describes when 2 threads are blocked forever, waiting for ea other

// immutability - immutable objects are important to concurrent development bc they can't be corrupted

public class Main {

    // implement synchronization, which is the tool we utilize to avoid
    // thread interference and memory consistency errors
    // NOTE: sync can also cause thread contention (2 or more threads try
    // to access same resource - same var/mem loc/obj)
    private String lastName;
    private int nameCount;
    private List<String> nameList = new ArrayList<>();

    public void addName(String name) {
        //this is a synchronized stmt
        synchronized (this) {
            lastName = name;
            nameCount++;
        }
        nameList.add(name);
    }

    //the first while is not good bc of flag or something.
    //I don't understand this at all
/*
    Suppose guardedJoy is a method that must
    not proceed until a shared variable joy has been set by another thread.

    Such a method could, in theory, simply loop until the condition is
    satisfied, but that loop is wasteful, since it executes continuously
    while waiting.
*/
//    public void guardedJoy() {
////        while(!joy) { }
//        while(!joy) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("Joy has been achieved");
//    }
//
//    public synchronized notifyAll() {
//        joy = true;
//        notifyAll();
//    }

    // within the Java API there is a thread object

    //this main method is the main thread
    public static void main(String[] args) throws InterruptedException {

        // create a new thread. the constructor below accepts something of type runnable
        // you can instantiate as many threads as you want
        // all threads accept a runnable object
        // threads resolve independently of when they're instantiated and started
        Thread thread1 = new Thread(new HelloRunnable("Hello from first thread"));
        Thread thread2 = new Thread(new HelloRunnable("Hello from second thread"));
        Thread thread3 = new Thread(new HelloRunnable("Hello from third thread"));
        thread1.start();
        thread2.start();
        thread3.start();

        // below is the utilization of a lamda for creating  a runnable
        // this is possible bc Runnable is a functional interface
        Runnable runnable = () -> System.out.println("Hello from lambda");

        Thread functionalThread = new Thread(runnable);
        functionalThread.start();

        Runnable pausableRunnable = () -> {
            try {
                System.out.println("Hold on, I have to take a quick nap...");
                Thread.sleep(6000);
                System.out.println("Hello from pausable lambda");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread pausedThread = new Thread(pausableRunnable);
        pausedThread.start();

        // an interrupt is an indication to a thread that it should stop and do s'thing else

        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

        Runnable myRunnableSync = () -> {
            synchronizedCounter.increment();
            System.out.println("# from sync: " + synchronizedCounter.value());
        };
        Thread myThreadSync = new Thread(myRunnableSync);
        Thread myOtherThreadSync = new Thread(myRunnableSync);

        //this join will make the effects of the code in the thread visible
        //to the thread that performed the join. this creates patience and consistency in execution
        myThreadSync.start();
//        myThreadSync.join(1000);
        myOtherThreadSync.start();
//        myOtherThreadSync.join(1000);
        System.out.println("Number from sync: " + synchronizedCounter.value());

/*
        class MyClass {
            private long c1 = 0;
            private long c2 = 0;
            private Object lock1 = new Object();
            private Object lock2 = new Object();

            public void inc1() {
                synchronized(lock1) {
                    c1++;
                }
            }

            public void inc2() {
                synchronized(lock2) {
                    c2++;
                }
            }
        }//end MyClass class
*/

    }//end main()

}//end Main class
