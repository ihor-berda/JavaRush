package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/


import java.io.FileInputStream;


public class Solution {
    public static void main(String[] args) throws Exception{
        FileInputStream in = new FileInputStream(args[0]);
        double countOfSymbols = 0;
        double countOfSpaces = 0;
        while (in.available() > 0) {
            if (in.read() == 32) countOfSpaces++;
            countOfSymbols++;
        }
        double result = countOfSpaces / countOfSymbols;
        System.out.println(Math.round(result*10000)/100.0000);
        in.close();
    }
}
