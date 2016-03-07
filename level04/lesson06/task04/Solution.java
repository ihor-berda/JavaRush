package com.javarush.test.level04.lesson06.task04;

/* Сравнить имена
Ввести с клавиатуры два имени, и если имена одинаковые вывести сообщение «Имена идентичны». Если имена разные, но их длины равны – вывести сообщение – «Длины имен равны».
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
        BufferedReader r = new BufferedReader (inputStreamReader);
        String a, b;
        a = r.readLine();
        b = r.readLine();
        if (a.equals(b))
            System.out.println ("Имена идентичны");
        else
        if (a.length()==b.length())
            System.out.println ("Длины имен равны");

    }
}
