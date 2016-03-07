package com.javarush.test.level15.lesson12.home04;

/**
 * Created by Igor on 06.08.2015.
 */
public class Sun implements Planet
{
    private static Sun instanse;
    private Sun(){}
    public static Sun getInstance() {
        if (instanse == null) {
            instanse = new Sun();
        }
        return instanse;
    }
}
