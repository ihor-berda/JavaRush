package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
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
        String file3 = reader.readLine();
        FileInputStream in = new FileInputStream(file);
        FileOutputStream out = new FileOutputStream(file2);
        FileOutputStream out2 = new FileOutputStream(file3);
        byte[] buffer = new byte [in.available() / 2 + in.available() % 2];
        byte[] buffer2 = new byte[in.available() / 2];
        out.write(buffer, 0, in.read(buffer));
        out2.write(buffer2, 0, in.read(buffer2));
        reader.close();
        in.close();
        out.close();
        out2.close();
    }
}
