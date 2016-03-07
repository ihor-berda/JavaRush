package com.javarush.test.level09.lesson11.home09;

import java.util.*;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        //напишите тут ваш код
        Map<String, Cat> map = new HashMap<String, Cat>();
        map.put("Vaska", new Cat ("Vaska"));
        map.put("Murka", new Cat ("Murka"));
        map.put("Kiska", new Cat ("Kiska"));
        map.put("Kisunya", new Cat ("Kisunya"));
        map.put("Barsik", new Cat ("Barsik"));
        map.put("Gavka", new Cat ("Gavka"));
        map.put("Cote", new Cat("Cote"));
        map.put("Mote", new Cat ("Mote"));
        map.put("Rizhui", new Cat ("Rizhui"));
        map.put("Markisa", new Cat ("Markisa"));
        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        Set<Cat> set = new HashSet<Cat>();
        Iterator<Map.Entry<String, Cat>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Cat s = iterator.next().getValue();
            set.add(s);
        }
        return set;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;


        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
