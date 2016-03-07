package com.javarush.test.level04.lesson13.task02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* Рисуем прямоугольник
Ввести с клавиатуры два числа m и n.
Используя цикл for вывести на экран прямоугольник размером m на n из восьмёрок.
Пример: m=2, n=4
8888
8888
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader r = new BufferedReader(inputStreamReader);
        String a, b;
        a = r.readLine();
        b = r.readLine();
        int m, n;
        m = Integer.parseInt(a);
        n = Integer.parseInt(b);
        for (int j = 0; j < m; j++)
        {
            for (int i = 0; i < n; i++)
            {
                System.out.print(8);
            }
            System.out.println("");
        }


    }
}
