package com.lex.virtual_thread._02;

/**
 * @author : Lex Yu
 * @date : 25/07/2023
 */
public class Demo {
    public static void main(String[] args) {

    }


    private static Thread virtualThread(String name, Runnable runnable){
        return Thread.ofVirtual()
                .name(name)
                .start(runnable);
    }
}

