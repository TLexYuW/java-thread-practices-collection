package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 10:15 pm
 */
public class Example_9 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int count = 1;
            while (true) {
                sleep(1000);
                System.out.println("Running ..." + count++);
            }
        };

        Thread thread = new Thread( runnable );
//        thread.setDaemon(true);
        thread.start();
        sleep(4000);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
