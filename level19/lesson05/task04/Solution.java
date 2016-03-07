package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> output = new ArrayList<String>();
        String line = "";
        while ((line = fileReader.readLine()) != null) {
            list.add(line);
        }
        for (String text : list) {
            String[] lines = text.split(" ");
            for (String s : lines) {
                output.add(s.replace(".", "!"));
            }
        }
        BufferedWriter fileWritter = new BufferedWriter(new FileWriter(reader.readLine()));
        for (String out : output) {
            fileWritter.write(out + " ");
        }
        reader.close();
        fileReader.close();
        fileWritter.close();
    }
}
