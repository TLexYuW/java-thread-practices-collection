package com.lex.thread.virtual;

/**
 * @author : LEX_YU
 * @date : 19/02/2023 3:05 pm
 */
public class VirtualThreadCustomSubclass extends Thread{
    @Override
    public void run() {
        System.out.println("Run = " + getName());
    }
}
