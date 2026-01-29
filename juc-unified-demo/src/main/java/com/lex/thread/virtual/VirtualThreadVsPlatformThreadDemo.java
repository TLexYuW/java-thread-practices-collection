package com.lex.thread.virtual;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 11:10 pm
 */
public class VirtualThreadVsPlatformThreadDemo {
    public static void main(String[] args) {

        List<Thread> vThreads = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        int vThreadCount = 100_000;
        int threadCount = 100_000;

        long start, end, period;

        // Example : Create and start virtual thread with lambda expression
//        /*
        start = System.currentTimeMillis();

        for (int i = 0; i < vThreadCount; i++) {
            int vThreadIndex = i;
            Thread vThread = Thread.ofVirtual().start(() -> {
                        int result = 1;
                        for (int j = 0; j < 10; j++) {
                            result *= (j + 1);
                        }
                        System.out.printf("Result [ %d ] : %d \n", vThreadIndex, result);
                    });
            vThreads.add(vThread);
        }

        for (int i = 0; i < vThreads.size(); i++) {
            try {
                vThreads.get(i).join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        end = System.currentTimeMillis();
        System.out.printf("Period = %d \n", (end - start));
//         */

        /*
        start = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new MyThread();
            thread.start();
            threads.add(thread);
        }

        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        end = System.currentTimeMillis();
        System.out.printf("Period = %d \n", (end - start));
         */
    }

}
