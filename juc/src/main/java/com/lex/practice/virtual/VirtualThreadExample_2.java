package com.lex.practice.virtual;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 11:10 pm
 */
public class VirtualThreadExample_2 {
    public static void main(String[] args) {

        List<Thread> vThreads = new ArrayList<>();

        int vThreadCount = 100_000;

        // Example : Create and start virtual thread with lambda expression

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
    }

}
