package com.lex.practice.raceconditions;

/**
 * @author : LEX_YU
 * @date : 22/02/2023 11:45 pm
 */
public class Counter {
    private long count = 0;

    public long incAndGet(){
        this.count++;
        return this.count;
    }

    public long get(){
        return this.count;
    }

    public void add(long value){
        this.count = this.count + value;
    }
}
