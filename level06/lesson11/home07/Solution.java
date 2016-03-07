package com.javarush.test.level06.lesson11.home07;

import java.util.ArrayList;

/* Три статические переменных name
Создай 3 public статических переменных: String Solution.name, String Cat.name, String Dog.name
*/

public class Solution
{
    static String name;
    Solution(String name)
    {
        Solution.name = name;
    }
    public static class Cat {
        static String name;
        Cat(String name)
        {
            Cat.name = name;
        }
    }

    public static class Dog {
        static String name;
        Dog (String name)
        {
            Dog.name = name;
        }
    }
}
