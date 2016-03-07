package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader r = new BufferedReader(inputStreamReader);
        String b = "сумма";
        int s = 0;
        while (true)
        {
            String a = r.readLine();
            if (a.equals(b))
            {
                System.out.println(s);
                break;
            }
            else
            {
                int a1 = Integer.parseInt(a);
                s = s + a1;
            }
        }
    }
}
