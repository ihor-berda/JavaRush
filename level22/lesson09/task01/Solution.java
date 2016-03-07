package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример, "мор"-"ром", "трос"-"сорт"
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        String line = "";
        List<String> words = new ArrayList<String>();
        while ((line = fileReader.readLine()) != null) {
            words.add(line);
        }
        Pair pair = null;
        for (String wordInLine : words) {
            String[] wordsBySpace = wordInLine.split(" ");
            for (String oneWord : wordsBySpace) {
                StringBuilder word = new StringBuilder(oneWord);
                word.reverse();
                String wordToFind = word.toString();
                for (String anotherWord : wordsBySpace) {
                    pair = new Pair();
                    if (anotherWord.equals(wordToFind)) {
                        pair.first = oneWord;
                        pair.second = anotherWord;
                        if (pair.first.compareTo(pair.second) < 0) {
                            result.add(pair);
                        }
                    }
                }
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
