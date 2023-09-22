package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class Example_6 {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Running ...");
        };

        Thread thread = new Thread( runnable, "The Thread 1" );
        thread.start();
        Thread thread2 = new Thread( runnable, "The Thread 2" );
        thread2.start();
    }

}
