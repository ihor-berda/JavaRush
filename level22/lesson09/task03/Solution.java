package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready())
        {
            String[] s = reader.readLine().split("\\s");
            Collections.addAll(list, s);
        }
        reader.close();
        String[] words = new String[list.size()];
        words = list.toArray(words);
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
        scanner.close();
    }

    public static StringBuilder getLine(String... words) {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, words);
        if (words == null || words.length == 0) return new StringBuilder();
        if ("".equals(words[0]) || words.length == 1) return new StringBuilder(words[0]);
        if (!words[0].isEmpty() && words.length == 1){
            return new StringBuilder(words[0]);
        }
        while (true) {
            Collections.shuffle(strings);
            int count = 0;
            for (int i = 0; i < words.length -1; i++) {
                char last = strings.get(i).toLowerCase().charAt(strings.get(i).length()-1);
                char fisrt = strings.get(i+1).toLowerCase().charAt(0);
                if (last == fisrt)
                    count++;
                else break;
            }
            if (count == words.length-1) {
                StringBuilder result = new StringBuilder(strings.get(0));
                for (int i = 1; i < strings.size(); i++) {
                    result.append(" ");
                    result.append(strings.get(i));
                }
                return result;
            }
            else continue;
        }
    }
}
