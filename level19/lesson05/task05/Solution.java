package com.javarush.test.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        String line = "";
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> withoutPunctuationList = new ArrayList<String>();
        while ((line = fileReader.readLine()) != null) {
            list.add(line);
        }
        for (String text : list) {
            String[] textWithSpaces = text.split(" ");
            for (String withSpaces : textWithSpaces) {
                withoutPunctuationList.add(withSpaces.replaceAll("\\p{P}", ""));
            }
        }
        BufferedWriter fileWritter = new BufferedWriter(new FileWriter(reader.readLine()));
        for (String s : withoutPunctuationList) {
            fileWritter.write(s + " ");
        }
        reader.close();
        fileReader.close();
        fileWritter.close();
    }
}
