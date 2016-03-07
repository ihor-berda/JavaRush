package com.javarush.test.level27.lesson02.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Рефакторинг
Перепишите код без использования меток, сохранив при этом логику.
Не оставляйте закомментированный код.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        int a;
        int sum = 0;
        do {
            a = Integer.parseInt(bis.readLine());
            sum += a;
        } while (isContinueWhile(sum));
        System.out.println(sum);
    }

    private static boolean isContinueWhile(int sum) {
        for (int k = 0; k < 100; k += 20)
            if (sum < 100 && k <= 20) {
                System.out.println(k < 2 ? k : sum % k);
            } else {
                continue;
            }

        return false;
    }
}
