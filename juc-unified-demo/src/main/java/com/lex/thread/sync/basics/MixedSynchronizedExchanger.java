package com.lex.thread.sync.basics;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 8:43 pm
 */
public class MixedSynchronizedExchanger {

    // differene monitor Object
    private static Object staticObject;

    public static synchronized void setStaticObject(Object obj) {
        staticObject = obj;
    }

    private static Object instanceObj;

    public  synchronized void setInstanceObject(Object obj) {
        this.instanceObj = obj;
    }

}
