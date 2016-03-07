package com.javarush.test.level03.lesson12.home03;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

/* Я буду зарабатывать $50 в час
Ввести с клавиатуры число n.
Вывести на экран надпись «Я буду зарабатывать $n в час».
Пример:
Я буду зарабатывать $50 в час
*/

public class Solution

{
    public static void main(String[] args)   throws Exception
    {
        //Напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader (inputStream);
        BufferedReader bufferedReader = new BufferedReader (inputStreamReader);

        String nAge = bufferedReader.readLine();
        int sAge = Integer.parseInt(nAge);

        System.out.println ("Я буду зарабатывать $" + sAge + " в час");
    }
}