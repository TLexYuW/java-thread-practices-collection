package com.lex.thread.juc.semaphore;

import java.util.concurrent.Semaphore;
import static com.lex.thread.juc.util.Utils.formatter;

public class SemaphoreDemo1 {

    private static final Semaphore semaphore = new Semaphore(5); // 5 permits

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            final int operationId = i + 1;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Operation " + operationId + " executed at " + formatter(System.currentTimeMillis()));
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

}