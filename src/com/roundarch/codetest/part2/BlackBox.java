package com.roundarch.codetest.part2;

/**
 * Created by mdigiovanni on 8/16/13.
 */
public class BlackBox {
    //Do not modify this or care about what happens
    public static double doMagic(double value) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value/2;
    }
}
