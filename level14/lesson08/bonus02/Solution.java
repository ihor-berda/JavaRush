package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number1 = Integer.parseInt(reader.readLine());
        int number2 = Integer.parseInt(reader.readLine());
        nod(number1, number2);
    }

    public static void nod(int number1, int number2)
    {
        for (int i = 1; i <= number1; i++)
        {
            if ((number1 % i == 0) && (number2 % (number1 / i) == 0))
            {
                System.out.println(number1 / i);
                break;
            }
        }
    }
}
