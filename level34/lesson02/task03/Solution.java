package com.javarush.test.level34.lesson02.task03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Разложение на множители с помощью рекурсии
Разложить целое число n > 1 на простые множители.
Вывести в консоль через пробел все множители в порядке возрастания.
Написать рекуррентный метод для вычисления простых множителей.
Не создавайте статические переменные и поля класса.
Пример:
132
Вывод на консоль:
2 2 3 11
*/
public class Solution {
    public void recursion(int n) {
        int a = 2;
        while (a <= n) {
            if ((n % a) == 0) {
                if (a != n) {
                    System.out.print(a + " ");
                    recursion(n / a);
                }
                else System.out.println(a);
                return;
            }
            a++;
        }
    }
}
