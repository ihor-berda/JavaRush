package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
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
        int a, b, c;
        String d, e, f;
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader r = new BufferedReader(inputStreamReader);
        d = r.readLine();
        e = r.readLine();
        f = r.readLine();
        a = Integer.parseInt(d);
        b = Integer.parseInt(e);
        c = Integer.parseInt(f);
        if (a > b && a > c)
        {
            System.out.println(a);
            if (b > c)
            {
                System.out.println(b);
                System.out.println(c);
            }
            if (c > b)
            {
                System.out.println(c);
                System.out.println(b);
            }
        }
        else
        if (b > a && b > c)
        {
            System.out.println(b);
            if (a > c)
            {
                System.out.println(a);
                System.out.println(c);
            }
            if (c > a)
            {
                System.out.println(c);
                System.out.println(a);
            }
        }
        else
        if (c > a && c > b)
        {
            System.out.println(c);
            if (a > b)
            {
                System.out.println(a);
                System.out.println(b);
            }
            else
            {
                System.out.println(b);
                System.out.println(a);
            }
        }
    }
}

