package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 10:23 pm
 */
public class Example_10 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 1; i <= 10; i++) {
                sleep(1000);
                System.out.println("Running... " + i);
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
//        /* Get The Main Thread Wait
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
//         */

        // Project Loom - fibers Java 14

    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
