package com.javarush.test.level10.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: ввести с клавиатуры 30 чисел. Вывести 10-е и 11-е минимальные числа.
Пояснение:
Самое минимальное число – 1-е минимальное.
Следующее минимальное после него – 2-е минимальное
Пример:
1 6 5  7  1  15   63   88
Первое минимальное – 1
Второе минимальное – 1
Третье минимальное – 5
Четвертое минимальное – 6
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[30];
        for (int i = 0; i < 30; i++)
        {
            array[i] = Integer.parseInt(reader.readLine());
        }

        sort(array);

       System.out.println(array[9]);
       System.out.println(array[10]);
    }

    public static void sort(int[] array)
    {
        //напишите тут ваш код
        for (int index1 = 0; index1 < array.length; index1++) {
            for (int index2 = 0; index2 < array.length - 1; index2++) {
                if (array[index2] > array[index2 + 1]) {
                    int tmp = array[index2 + 1];
                    array[index2 + 1] = array[index2];
                    array[index2] = tmp;
                }
            }
        }
    }
}
