package com.lex.practice.volatilekeyword;

/**
 * @author : LEX_YU
 * @date : 11/01/2023 10:04 pm
 */
public class Counter {
    private volatile int count = 0;

    public boolean inc(){
        if (this.count == 10){
            return false;
        }
        this.count++;
        return true;
    }
}
