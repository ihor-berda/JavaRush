package com.javarush.test.level14.lesson08.bonus01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        int j =0 ;
        while (j < 10)
        {
            try
            {
                float i = 1 / 0;

            }
            catch (Exception e)
            {
                exceptions.add(e);
            }
            j++;
        }
        //Add your code here



    }
}
