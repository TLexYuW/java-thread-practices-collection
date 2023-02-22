package com.lex.practice.memory;

/**
 * @author : LEX_YU
 * @date : 06/01/2023 10:25 pm
 */
public class SeparateObjects {
    public static void main(String[] args) {
        Object myObject = new Object();

        Runnable runnable1 = new MyRunnable(myObject);
        Runnable runnable2 = new MyRunnable(myObject);

        Thread thread1 = new Thread(runnable1, "Thread 1");
        Thread thread2 = new Thread(runnable2, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
