package com.javarush.test.level10.lesson04.task01;

/* Задача №1 на преобразование целых типов
Расставь где нужно оператор приведения типа:
byte a = 1234;
int b = a;
byte c = a * a;
int d = a / c;
*/

public class Solution
{
    public static void main(String[] args)
    {
        byte a = (byte) 1234;
        int b = a;
        byte c = (byte) (a * a);
        int d = a / c;
    }
}
