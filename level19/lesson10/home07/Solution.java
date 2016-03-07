package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        List<String> lines_File = new ArrayList<String>();
        List<String> biggerThanSix = new ArrayList<String>();
        String line = "";
        while ((line = reader.readLine()) != null) {
            lines_File.add(line);
        }
        String[] lineByWords = null;
        for (String lines : lines_File) {
            lineByWords = lines.split(" ");
            for (String words : lineByWords) {
                if (words.length() > 6) {
                    biggerThanSix.add(words + ",");
                }
            }
        }
        for (int i = 0; i < biggerThanSix.size(); i++)
        {
            String out = biggerThanSix.get(i);
            if (i == biggerThanSix.size() - 1) {
                out = out.substring(0, out.indexOf(","));
            }
            writer.write(out);
        }
        reader.close();
        writer.close();

//        String firstFile = args[0];
//        String secondFile = args[1];
//
//        BufferedReader reader = new BufferedReader(new FileReader(firstFile));
//        BufferedWriter writer = new BufferedWriter(new FileWriter(secondFile));
//        String line;
//        String string = "";
//        String [] word;
//
//        while ((line = reader.readLine())!=null)
//        {
//            string = string + line+ " ";
//        }
//
//        word = string.split(" ");
//        boolean firstFlag = true;
//        for (String a : word)
//        {
//
//            if (a.length()>6)
//            {
//                if (!firstFlag)
//                    writer.write(',');
//                writer.write(a);
//                firstFlag = false;
//
//            }
//
//        }
//
//
//        reader.close();
//        writer.close();
    }
}
