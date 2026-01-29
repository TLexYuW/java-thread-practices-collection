package com.lex.thread.basics;

/**
 * 展示目的：展示守護執行緒 (Daemon) 在主執行緒結束時會隨之被 JVM 強制終止的特性
 * @author : LEX_YU
 * @date : 05/01/2023 10:15 pm
 */
public class DaemonThreadImpactDemo {
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
