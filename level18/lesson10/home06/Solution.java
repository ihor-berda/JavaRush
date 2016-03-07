package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import com.javarush.test.level15.lesson12.home03.Tree;

import java.io.FileInputStream;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream(args[0]);
        String english = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        while (true) {if (!args[0].contains(english)) break;}


        TreeMap<Character, Integer> result = new TreeMap<Character, Integer>();

        while (inputStream.available() > 0) {
            int i = (char)inputStream.read();
            char ch = (char) i;
            if (!result.containsKey(ch))
                result.put(ch, 1);
            else result.put(ch, result.get(ch) + 1);
        }


        for (Map.Entry<Character, Integer> pair : result.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }

        inputStream.close();
    }
}
