package com.javarush.test.level06.lesson08.task02;

/* Статические методы: int getCatCount() и setCatCount(int)
Добавить  к классу Cat два статических метода: int getCatCount() и setCatCount(int), с помощью которых можно получить/изменить количество котов (переменную catCount)
*/

public class Cat
{
    private static int catCount = 0;

    public Cat()
    {
        catCount++;
    }

    public static int getCatCount()
    {
        //Напишите тут ваш код
        return Cat.catCount;

    }

    public static void setCatCount(int catCount)
    {
        //Напишите тут ваш код
        Cat.catCount = catCount;

    }
}
