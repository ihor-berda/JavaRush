package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream in = new FileInputStream(reader.readLine());
        FileOutputStream out = new FileOutputStream(reader.readLine());
        while (in.available() > 0) {
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            for (int i = 0; i < buffer.length; i++)
            {
                if (i % 2 != 0) out.write(buffer[i]);
            }
        }
        reader.close();
        in.close();
        out.close();
    }
}
