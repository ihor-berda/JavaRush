package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<String, String>();
        map.put("Алибабай", "Олексей");
        map.put("Бабай", "Олексей");
        map.put("Бур", "Мария");
        map.put("Мур", "Мария");
        map.put("Алибабай", "Александр");
        map.put("Мур", "Александр");
        map.put("Кот", "Екатерина");
        map.put("Бур", "Игорь");
        map.put("Бурай", "Яна");
        map.put("Бабай", "Павел");
        return (HashMap<String, String>)map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //напишите тут ваш код
        int countName = 0;
        Iterator<Map.Entry<String, String>>iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            if(name.equals(iterator.next().getValue()))
                countName++;
        }
        return countName;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String familiya)
    {
        //напишите тут ваш код
        int countFamiliya = 0;
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            if (familiya.equals(iterator.next().getKey()))
                countFamiliya++;
        }
        return countFamiliya;
    }
}
