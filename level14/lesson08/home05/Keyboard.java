package com.javarush.test.level14.lesson08.home05;

/**
 * Created by Igor on 03.08.2015.
 */
public class Keyboard implements CompItem {

    @Override
    public String getName()
    {
        return this.getClass().getSimpleName();
    }
}
