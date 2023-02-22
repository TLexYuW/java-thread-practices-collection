package com.lex.practice.sync;

import netscape.javascript.JSObject;

import java.util.function.Consumer;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 10:59 pm
 */
public class SynchronizedLambda {
    private static Object object;

    public static synchronized void setObject(Object o) {
        object = o;
    }

    public static void consumeObject(Consumer consumer) {
        consumer.accept(object);
    }

    public static void main(String[] args) {
        consumeObject(obj -> {
            synchronized (SynchronizedLambda.class) {
                System.out.println(obj);
            }
        });

        consumeObject(obj -> {
            synchronized (JSObject.class) {
                System.out.println(obj);
            }
        });
    }
}
