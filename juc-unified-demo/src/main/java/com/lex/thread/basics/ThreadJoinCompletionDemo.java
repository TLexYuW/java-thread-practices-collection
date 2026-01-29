package com.lex.thread.basics;

/**
 * 展示目的：展示如何使用 join() 確保主執行緒在子執行緒執行完成後才繼續向下執行
 * @author : LEX_YU
 * @date : 05/01/2023 10:23 pm
 */
public class ThreadJoinCompletionDemo {
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
