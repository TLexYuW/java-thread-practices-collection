package com.lex.practice.threadlocal;

/**
 * @author : LEX_YU
 * @date : 19/02/2023 4:03 pm
 */
public class ThreadLocalLazyInitExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        String value = threadLocal.get();
        if (value == null) {
            threadLocal.set("Lazily set value");
            value = threadLocal.get();
        }

        System.out.println(value);
    }
}
