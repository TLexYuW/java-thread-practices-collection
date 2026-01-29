package com.lex.thread.sync.basics;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 11:39 pm
 */
public class SyncCounter {
    private long count = 0;

    /*
    public synchronized void incCount(){
        this.count++;
    }

    public synchronized long getCount(){
        return this.count;
    }
    */

//    /*
    public void incCount(){
        this.count++;
    }

    public long getCount(){
        return this.count;
    }
//     */
}
