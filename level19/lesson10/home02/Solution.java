package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        Map<String, Double> map = new HashMap<String, Double>();
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
        ArrayList<Double> newValues = new ArrayList<>();
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            newValues.add(pair.getValue());
        }
        Double max = 0d;
        for (int i = 0; i < newValues.size(); i++)
        {
            try
            {
                if (newValues.get(i) > newValues.get(i + 1))
                {
                    max = newValues.get(i);
                } else max = newValues.get(i + 1);
            }
            catch (IndexOutOfBoundsException e) {}
        }
        System.out.println(getKey(map, max));
    }
    public static Object getKey (Map map, Double value) {
        for (Object o : map.keySet()) {
            if (map.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
