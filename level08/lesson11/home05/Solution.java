package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Character.toUpperCase;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        char[] array = s.toCharArray();
        array[0] = toUpperCase(array[0]);
        for (int i = 0; i < array.length-1; i++) {
            if ((array[i] == ' ' && array[i + 1] != ' ')) {
                array[i + 1] = toUpperCase(array[i + 1]);
            }
        }
        //напишите тут ваш код
        String text = new String(array);
        System.out.println(text);
    }


}
