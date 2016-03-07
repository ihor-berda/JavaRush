package com.javarush.test.level04.lesson06.task05;

/* 18+
Ввести с клавиатуры имя и возраст. Если возраст меньше 18 вывести надпись «Подрасти еще».
*/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader r = new BufferedReader(inputStreamReader);
        String a,b;
        a = r.readLine();
        b = r.readLine();
        int c;
        c = Integer.parseInt(b);
        if (c < 18)
            System.out.println ("Подрасти еще");

    }
}
