package com.lex.practice.memory;

/**
 * @author : LEX_YU
 * @date : 06/01/2023 11:37 pm
 */
public class SharedObjects {

    public static void main(String[] args) {
        Object myObject = new Object();

        Runnable runnable = new MyRunnable(myObject);

        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");

        thread1.start();
        thread2.start();

    }
}
