package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        ArrayList<String> digit = new ArrayList<String>();
        ArrayList<String> text = new ArrayList<String>();
        String line = "";
        while ((line = reader.readLine()) != null) {
            text.add(line);
        }
        for (String temp : text)
        {
            String[] space = temp.split(" ");
            for (String temp2 : space) {
                if (temp2.matches(".*\\d.*")) {
                    digit.add(temp2);
                }
            }
        }
        for (String outPut : digit) {
            writer.write(outPut + " ");
        }
        reader.close();
        writer.close();
    }
}
