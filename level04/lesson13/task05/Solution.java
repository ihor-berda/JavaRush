package com.javarush.test.level04.lesson13.task05;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* Все любят Мамбу
Ввести с клавиатуры имя и используя цикл for 10 раз вывести: [*имя* любит меня.]
Пример текста:
Света любит меня.
Света любит меня.
…
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader r = new BufferedReader(inputStreamReader);
        String s;
        s = r.readLine();
        for (int i = 0; i<10; i++){
            System.out.println(s + " любит меня.");
        }

    }
}
