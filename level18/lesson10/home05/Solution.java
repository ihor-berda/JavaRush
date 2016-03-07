package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFile = reader.readLine();
        String secondFile = reader.readLine();
        RandomAccessFile fileOne = new RandomAccessFile(firstFile, "r");
        RandomAccessFile fileTwo = new RandomAccessFile(secondFile, "rw");
        String s;
        while (true) {
            s = fileOne.readLine();
            if (s == null) break;
            String[] tmp = s.split(" ");
            for (String ss : tmp) {
                int i = (int) Math.round(Double.parseDouble(ss));
                fileTwo.writeBytes(String.valueOf(i) + " ");
            }
        }
        reader.close();
        fileOne.close();
        fileTwo.close();
    }
}
