package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Project Loom is the project that brought virtual threads to Java.
 * Virtual threads are lightweight, user-mode threads managed by the JVM,
 * allowing massive concurrency
 *
 * ExecutorService does not required join because it
 * complete background thread and at last it execute main thread
 */
public class Main {
    public static void main(String[] args) {
        Runnable task1 =  new Task("task 1");
        Runnable task2 =  new Task("task 2");

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();



//        Thread t1 = Thread.ofVirtual().start(task1);
//        Thread t2  = Thread.ofVirtual().start(task2);

        executor.submit(task1);
        executor.submit(task2);

//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
            System.out.println(" main thread continued ..."+ Thread.currentThread().getName());


    }
}

class Task implements Runnable{

    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        System.out.println(name +" is running "+ Thread.currentThread().getName());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(name+"is interrupted "+ Thread.currentThread().getName());
        }
        System.out.println(name+" completed "+ Thread.currentThread().getName());

    }
}