package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        FileInputStream in1 = new FileInputStream(fileName1);
        FileInputStream in2 = new FileInputStream(fileName2);
        byte[] byte1 = new byte[in1.available()];
        byte[] byte2 = new byte[in2.available()];
        in1.read(byte1);
        in2.read(byte2);
        FileOutputStream out = new FileOutputStream(fileName1, false);
        out.write(byte2);
        out = new FileOutputStream(fileName1, true);
        out.write(byte1);
        reader.close();
        in1.close();
        in2.close();
        out.close();
    }
}
