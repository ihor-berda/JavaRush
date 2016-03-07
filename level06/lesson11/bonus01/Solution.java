package com.javarush.test.level06.lesson11.bonus01;

/* Нужно исправить программу, чтобы компилировалась и работала
Задача: Программа вводит два числа с клавиатуры и выводит их максимум в виде «Max is 25»
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution
{
    public static int max = 100;
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        String max = "Max is ";
        int max1;
        String s = reader.readLine();
        String s1 = reader.readLine();
        int a = Integer.parseInt(s);
        int b = Integer.parseInt(s1);
        max1 = a > b ? a : b;

        System.out.println("Max is " + max1);
    }

}
