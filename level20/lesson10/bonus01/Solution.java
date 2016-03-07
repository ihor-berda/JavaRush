package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        ArrayList<Integer>num = new ArrayList<Integer>();
        for (int i = 1; i < N; i++) {
            int temp = i;
            String t = temp + "";
            int length = t.length();
            int sum = 0;
            while (temp != 0) {
                int digit = temp % 10;
                sum = sum + power(digit, length);
                temp = temp / 10;
            }
            if (sum == i) {
                num.add(i);
            }
            else {
                sum = 0;
            }
        }
        int [] result = new int[num.size()];
        for (int i = 0; i < num.size(); i++)
        {
            result[i] = num.get(i);
        }
        for (int i : result) {
            System.out.println(i);
        }
        return result;
    }
    static int power(int n, int r) {
        int c, p = 1;

        for (c = 1; c <= r; c++)
            p = p*n;

        return p;
    }

    public static void main(String[] args)
    {
        Long t0 = System.currentTimeMillis();
        int[] m = getNumbers(1000000);
        Long t1 = System.currentTimeMillis();
        for (int a : m) {
            System.out.println(a);
        }
        System.out.println("Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024d) + " Mb.");
        System.out.println("Time: " + (t1 - t0)/1000d + " sec.");
    }
}
