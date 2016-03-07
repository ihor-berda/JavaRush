package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) {
            throw new TooShortStringException();
        }
        if (string.equals("")) {
            throw new TooShortStringException();
        }
        int firstTab = string.indexOf("\t");
        if (firstTab == -1) {
            throw new TooShortStringException();
        }
        int secondTab = string.indexOf("\t", firstTab + 1);
        if (secondTab == -1) {
            throw new TooShortStringException();
        }
        string = string.substring(firstTab + 1, secondTab);
        return string;
    }

    public static class TooShortStringException extends Exception {
    }
}
