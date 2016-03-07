package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        String file2 = reader.readLine();
        FileInputStream in = new FileInputStream(file);
        FileOutputStream out = new FileOutputStream(file2);
        byte[] buffer = new byte[in.available()];
        in.read(buffer);
        for (int i = buffer.length - 1; i >= 0; i--) {
            out.write(buffer[i]);
        }
        reader.close();
        in.close();
        out.close();
    }
}
