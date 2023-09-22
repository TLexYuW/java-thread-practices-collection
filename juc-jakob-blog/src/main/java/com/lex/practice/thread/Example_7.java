package com.lex.practice.thread;

import java.util.Random;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class Example_7 {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Running ...");
            Random random = new Random();
            int num = random.nextInt(10000);
            try {
                Thread.sleep(num);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
            System.out.println(threadName + " Finished ...");
        };

        Thread thread = new Thread( runnable, "The Thread 1" );
        thread.start();
        Thread thread2 = new Thread( runnable, "The Thread 2" );
        thread2.start();
    }

}
