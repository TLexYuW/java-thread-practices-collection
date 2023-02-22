package com.lex.practice.threadlocal;

/**
 * @author : LEX_YU
 * @date : 19/02/2023 3:24 pm
 */
public class ThreadLocalInitialValueExample {
    public static void main(String[] args) {
        ThreadLocal<Object> threadLocal1 = new ThreadLocal<>() {
            @Override
            protected Object initialValue() {
                return new Object();
            }
        };

        ThreadLocal<Object> threadLocal2 = ThreadLocal.withInitial(Object::new);

        Thread thread1 = new Thread(()->{
            System.out.println("threadlocal1: " + threadLocal1.get());
            System.out.println("threadlocal2: " + threadLocal2.get());
        });

        Thread thread2 = new Thread(()->{
            System.out.println("threadlocal1: " + threadLocal1.get());
            System.out.println("threadlocal2: " + threadLocal2.get());
        });

        thread1.start();
        thread2.start();
    }
}
