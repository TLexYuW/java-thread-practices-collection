package com.lex.thread.sync.basics;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 10:38 pm
 */
public class SharedMonitorObjects {

    private Object monitor = null;
    private int counter = 0;

    public SharedMonitorObjects(Object monitor) {
        if (monitor == null) {
            throw new IllegalArgumentException(
                    "Monitor Object cannot be null"
            );
        }
        this.monitor = monitor;
    }

    public void Counter(){
        synchronized (this.monitor){
            this.counter++;
            System.out.println(counter);
        }
    }

}
