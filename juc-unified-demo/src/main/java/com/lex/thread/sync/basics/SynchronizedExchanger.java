package com.lex.thread.sync.basics;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 8:43 pm
 */
public class SynchronizedExchanger {
    private Object object;

    public synchronized Object getObject() {
        return object;
    }

    public synchronized void setObject(Object object) {
        this.object = object;
    }

    public Object getObj() {
        synchronized (this) {
            return object;
        }
    }

    public void setOb(Object object) {
        synchronized (this) {
            this.object = object;
        }
    }

}
