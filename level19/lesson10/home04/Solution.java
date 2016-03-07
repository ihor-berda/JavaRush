package com.javarush.test.level19.lesson10.home04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки. Не использовать try-with-resources

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        ArrayList<String> textInFile = new ArrayList<String>();
        String text = "";
        int index = 0;
        while ((text = fileReader.readLine()) != null) {
            textInFile.add(text);
        }
        for (String ss : textInFile)
        {
            String[] textWithSpace = ss.split(" ");
            for (String temp : textWithSpace)
            {
                for (String s : words)
                {
                    if (s.equals(temp))
                    {
                        index++;
                    }
                }
            }
            if (index < 2) index = 0;
            if (index == 2)
            {
                System.out.println(ss);
                index = 0;
            }
            else index = 0;
        }
        reader.close();
        fileReader.close();
    }
}
