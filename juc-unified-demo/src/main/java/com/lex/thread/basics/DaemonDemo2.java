package com.lex.thread.basics;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author : Lex Yu
 */
public class DaemonDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int time = 10; time > 0; --time) {
                System.out.println("Time #" + time);
                try {
                    SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t = new Thread(r);
        t.setDaemon(false);  // try to set this to "false" and see what happens
        t.start();

        System.out.println("Main thread waiting...");
        SECONDS.sleep(6);
        System.out.println("Main thread exited.");
    }
}
