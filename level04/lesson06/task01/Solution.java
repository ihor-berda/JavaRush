package com.javarush.test.level04.lesson06.task01;

/* Минимум двух чисел
Ввести с клавиатуры два числа, и вывести на экран минимальное из них.
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
            int a, b;
            String c, d;
            InputStream inputStream = System.in;
            Reader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader r = new BufferedReader(inputStreamReader);
            c = r.readLine();
            d = r.readLine();
            a = Integer.parseInt(c);
            b = Integer.parseInt(d);
            if(a < b) System.out.println(a);
            else System.out.println(b);


        }

        /* данные для тестирования:
        введите 1 и 2, минимум =  1
        введите 2 и 1, минимум =  1
        введите 1 и 1, минимум =  1
        введите -1 и -2, минимум =  -2
         */
    }