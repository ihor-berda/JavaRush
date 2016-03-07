package com.javarush.test.level05.lesson12.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Написать программу, которая:
1. вводит с консоли число N > 0
2. потом вводит N чисел с консоли
3. выводит на экран максимальное из введенных N чисел.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //напишите здесь ваш код
        int b = Integer.parseInt((reader.readLine()));
        if (b > 0)  {
            int c = Integer.parseInt((reader.readLine()));
            for (int i = 1; i <= b-1; i++)// не забудь после действия выше уменьшить цикл на 1
            {
                int w = Integer.parseInt(reader.readLine());
                if (w > c) {
                    c = w;
                }
            }
            int maximum = c;
            System.out.print(maximum);//здесь надо использовать System.out.Print(); у меня поэтому не принимало.
        }
    }
}
