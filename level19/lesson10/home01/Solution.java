package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        Map<String, Double> map = new TreeMap<String, Double>();
        ArrayList<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<Double> values = new ArrayList<Double>();
        String[] text;
        for (String s : lines) {
            text = s.split(" ");
            keys.add(text[0]);
            values.add(Double.parseDouble(text[1]));
        }
        for (int i = 0; i < keys.size(); i++)
        {
            map.put(keys.get(i), values.get(i));
        }
        ArrayList<String> ignoredKeys = new ArrayList<String>();
        for (int i = 0; i < keys.size() - 1; i++)
        {
            for (int j = i + 1; j < keys.size(); j++)
            {
                if (keys.get(i).equals(keys.get(j)) && !ignoredKeys.contains(keys.get(i))) {
                    Double tmp = values.get(i) + values.get(j);
                    map.put(keys.get(i), tmp);
                    ignoredKeys.add(keys.get(i));
                }
            }
        }
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}
