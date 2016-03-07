package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream(args[0]);
        String english = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] chars = english.toCharArray();
        int count = 0;
        while (in.available() > 0) {
            int data = in.read();
            for (int i = 0; i < chars.length; i++) {
                if (data == chars[i]) count++;
            }
        }
        System.out.println(count);
        in.close();
    }
}
