package com.javarush.test.level36.lesson08.task01;

import java.io.*;
import java.util.*;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        String text = reader(file);
        List<String> stringList = Arrays.asList(text.toLowerCase().split(""));
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < stringList.size(); i++) {
            String s = stringList.get(i);
            if((s.matches("\\w")) && (s.matches("[^0-9]")))
                set.add(stringList.get(i));
        }
        Iterator<String> iterator = set.iterator();
        if (set.size() < 5) {
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }
        }
        else {
            int count = 0;
            while (iterator.hasNext() && count < 5) {
                System.out.print(iterator.next());
                count++;
            }
        }
    }

    public static String reader(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        String res = "";
        int b = raf.read();
        while(b != -1){
            res = res + (char)b;
            b = raf.read();
        }
        raf.close();
        return res;
    }
}
