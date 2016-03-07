package com.javarush.test.level04.lesson06.task02;

/* Максимум четырех чисел
Ввести с клавиатуры четыре числа, и вывести максимальное из них.
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
        int a, b, e, f;
        String c, d, c1, d1;
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader r = new BufferedReader(inputStreamReader);
        c = r.readLine();
        d = r.readLine();
        c1 = r.readLine();
        d1 = r.readLine();
        a = Integer.parseInt(c);
        b = Integer.parseInt(d);
        e = Integer.parseInt(c1);
        f = Integer.parseInt(d1);
        if (a > b && a > e && a > f)
            System.out.println (a);
        if (b > a && b > e && b > f)
            System.out.println (b);
        if (e > a && e > b && e > f)
            System.out.println (e);
        if (f > a && f > b && f > e)
            System.out.println (f);


        }

    }
