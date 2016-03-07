package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by Igor on 04.08.2015.
 */
public class Singleton {
    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    private Singleton() {}
}
