package com.lex.practice.threadlocal;

/**
 * @author : LEX_YU
 * @date : 19/02/2023 2:45 pm
 */
public class ThreadLocalRemoveExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            threadLocal.set("Thread 1 Value");

            String value = threadLocal.get();
            System.out.println(value);

            threadLocal.remove();
            value = threadLocal.get();
            System.out.println(value);
        });

        Thread thread2 = new Thread(() -> {
            threadLocal.set("Thread 2 Value");
            String value = threadLocal.get();
            System.out.println(value);

            try {
                Thread.sleep(3000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            value = threadLocal.get();
            System.out.println(value);

            threadLocal.remove();
            value = threadLocal.get();
            System.out.println(value);

        });

        thread1.start();
        thread2.start();
    }
}
