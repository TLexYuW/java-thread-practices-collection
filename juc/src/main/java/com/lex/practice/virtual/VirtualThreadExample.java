package com.lex.practice.virtual;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 11:10 pm
 */
public class VirtualThreadExample {
    public static void main(String[] args) {

        Runnable runnable = () -> {
            for (int i = 1; i < 11; i++) {
                System.out.println("Index: " + i);
            }
        };

        Thread vThread1 = Thread.ofVirtual().start( runnable );

        // Example 2: Create but do not start virtual thread
        Thread vThreadUnstarted = Thread.ofVirtual().unstarted(runnable);
        vThreadUnstarted.start();


        // Example 4: How to join a virtual thread
        try {
            vThreadUnstarted.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
