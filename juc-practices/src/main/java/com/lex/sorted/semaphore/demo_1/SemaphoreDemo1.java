package com.lex.sorted.semaphore.demo_1;


import java.util.concurrent.Semaphore;

import static com.lex.sorted.util.Utils.formatter;

public class SemaphoreDemo1 {

    private static final Semaphore semaphore = new Semaphore(5); // 每秒最多 5 次

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            try {
                semaphore.acquire();

                System.out.println("Operation " + (i + 1) + " executed at " + formatter(System.currentTimeMillis()));

                Thread.sleep(1000);

                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
