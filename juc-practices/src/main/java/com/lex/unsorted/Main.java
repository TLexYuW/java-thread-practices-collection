package com.lex.unsorted;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author : LEX_YU
 * @date : 19/03/2023
 */
public class Main {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
//        IntStream.range(0,1000)
//                .parallel()
//                .forEach(i -> {
//                    executor.submit(Main::increment1);
//                    System.out.println("Thread id: " + Thread.currentThread().getId());
//                }); // 9xx
        IntStream.range(0,1000)
//                .parallel()
                .forEach(i -> {
                    executor.submit(Main::increment2);
                    System.out.println("Thread id: " + Thread.currentThread().getId());
                }); // 1000
//        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5_000);
        System.out.println(count);

        executor.shutdown();
    }
    static void increment1(){
        count += 1;
    }
    static synchronized void increment2(){
        count += 1;
    }
}
