package com.lex.thread.sync.basics;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 10:38 pm
 */
public class MultipleMonitorObjects {
    private Object monitor1 = new Object();
    private Object monitor2 = new Object();

    private int counter1 = 0;
    private int counter2 = 0;

    public void Counter1(){
        synchronized (this.monitor1){
            this.counter1++;
        }
    }

    public void Counter2(){
        synchronized (this.monitor2){
            this.counter2++;
        }
    }
}
