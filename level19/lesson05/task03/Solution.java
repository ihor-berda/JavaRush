package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        FileWriter fileWriter = new FileWriter(reader.readLine());
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        String line = "";
        while ((line = fileReader.readLine()) !=  null) {
            list.add(line);
        }
        for (String s : list) {
            String[] lines = s.split(" ");
            for (String ss : lines) {
                try {
                    numbers.add(Integer.parseInt(ss));
                }
                catch (NumberFormatException e) {}
            }
        }
        for (int i : numbers) {
            fileWriter.write(i + " ");
        }
        reader.close();
        fileReader.close();
        fileWriter.close();
    }
}
