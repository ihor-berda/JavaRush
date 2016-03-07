package com.javarush.test.level04.lesson16.home02;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        //Напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader r = new BufferedReader(inputStreamReader);
        String a, b, c;
        a = r.readLine();
        b = r.readLine();
        c = r.readLine();
        int a1, b1, c1;
        a1 = Integer.parseInt(a);
        b1 = Integer.parseInt(b);
        c1 = Integer.parseInt(c);
        if (a1 > b1 && b1 > c1)
            System.out.println (b1);
        else if (b1 > c1 && c1 > a1)
                System.out.println (c1);
        else
                if (c1 > a1 && a1 > b1)
                    System.out.println (a1);
        else
                    if (b1 > a1 && a1 > c1)
                        System.out.println (a1);
        else
                        if (c1 > b1 && b1 > a1)
                            System.out.println (b1);
        else
                            if (a1 > c1 & c1 > b1)
                                System.out.println (c1);
    }
}
