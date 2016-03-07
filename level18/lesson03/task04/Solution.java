package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream in = new FileInputStream(reader.readLine());
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        while (in.available() > 0)
        {
            int key = in.read();
            if (map.containsKey(key))
            {
                int value = map.get(key);
                map.put(key, ++value);
            } else map.put(key, 1);
        }
        int tempValue = 2147483647;
        for (Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            if (tempValue > pair.getValue())
                tempValue = pair.getValue();
        }
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            if (pair.getValue().equals(tempValue))
                System.out.print(pair.getKey() + " ");
        }
        reader.close();
        in.close();
    }
}
