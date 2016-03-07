package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать содержимое третьего файла
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
        String fileName3 = reader.readLine();
        FileOutputStream out = new FileOutputStream(fileName1);
        FileInputStream in1 = new FileInputStream(fileName2);
        FileInputStream in2 = new FileInputStream(fileName3);
        while (in1.available() > 0) {
            int data = in1.read();
            out.write(data);
        }
        while (in2.available() > 0) {
            int data = in2.read();
            out.write(data);
        }
        reader.close();
        out.close();
        in1.close();
        in2.close();
    }
}
