package com.lex.sorted.lock.reentrant;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author : LEX_YU
 */
public class ReentrantLockDemo {
    private static int count = 0;
    ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo case2 = new ReentrantLockDemo();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        IntStream.range(0, 1000)
                .parallel()
                .forEach(i -> {
                    System.out.println(Thread.currentThread().getName());
                    executor.submit(case2::increment);
                });
        Thread.sleep(5_000);
        System.out.println(count);
        executor.shutdown();
    }

    void increment() {
        lock.lock();
        try {
            count += 1;
        } finally {
            lock.unlock();
        }
    }
}
