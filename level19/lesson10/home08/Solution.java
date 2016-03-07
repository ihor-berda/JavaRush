package com.javarush.test.level19.lesson10.home08;

/* Перевертыши
1 Считать с консоли имя файла.
2 Для каждой строки в файле:
2.1 переставить все символы в обратном порядке
2.2 вывести на экран
3 Закрыть потоки. Не использовать try-with-resources

Пример тела входного файла:
я - программист.
Амиго

Пример результата:
.тсиммаргорп - я
огимА
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        List<String> lines_File = new ArrayList<String>();
        List<String> reverse = new ArrayList<String>();
        String line = "";
        while ((line = fileReader.readLine()) != null) {
            lines_File.add(line);
        }
        for (String lines : lines_File) {
            reverse.add(new StringBuilder().append(lines).reverse().toString());
        }
        for (String out : reverse) {
            System.out.println(out);
        }
        reader.close();
        fileReader.close();
    }
}
