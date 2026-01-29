package com.lex.thread.sync.basics;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 11:25 pm
 */
public class Reentrance {
    private int count = 0;

    public synchronized void inc(){
        this.count++;
    }

    public synchronized int incAndGet(){
        inc();
        return this.count;
    }
}
