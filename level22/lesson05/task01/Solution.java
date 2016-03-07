package com.javarush.test.level22.lesson05.task01;

import com.javarush.test.level22.lesson05.home01.TooShortStringFirstThreadException;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null || string.isEmpty()) {
            throw new TooShortStringException();
        }
        int firstSpace = string.indexOf(" ") + 1;
        char[] chars = string.toCharArray();
        int space = 0;
        int lastSpace = 0;
        for (int i = 0; i < string.length(); i++) {
            if (chars[i] == ' ') {
                space++;
                if (space == 4) {
                    lastSpace = string.length();
                }
                else if (space == 5) {
                    lastSpace = i;
                    break;
                }
            }
        }
        if (space < 4) throw new TooShortStringException();
        string = string.substring(firstSpace, lastSpace);
        return string;
    }

    public static class TooShortStringException extends Throwable
    {
    }

    public static void main(String[] args) throws  TooShortStringException
    {
        System.out.println(getPartOfString(null));
    }
}
