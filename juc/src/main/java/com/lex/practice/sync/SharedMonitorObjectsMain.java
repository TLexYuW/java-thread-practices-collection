package com.lex.practice.sync;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 10:50 pm
 */
public class SharedMonitorObjectsMain {
    public static void main(String[] args) {
        // do not use String as monitor
        // use object or instance of own classes

        Object monitor1 = new Object();
        SharedMonitorObjects smo1 = new SharedMonitorObjects(monitor1);
        SharedMonitorObjects smo2 = new SharedMonitorObjects(monitor1);

        smo1.Counter();
        smo2.Counter();
        System.out.println(smo1 + ", " + smo2);

        Object monitor2 = new Object();
        SharedMonitorObjects smo3 = new SharedMonitorObjects(monitor2);
        smo3.Counter();
        System.out.println(smo3);

    }
}
