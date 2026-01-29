package com.lex.thread.basics;

import java.util.Random;

/**
 * 展示目的：展示執行緒在休眠期間處理中斷異常 (InterruptedException) 的正確方式
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class ThreadSleepAndInterruptDemo {

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
