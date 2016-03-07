package com.javarush.test.level05.lesson12.home03;

/* Создай классы Dog, Cat, Mouse
Создай классы Dog, Cat, Mouse. Добавь по три поля в каждый класс, на твой выбор. Создай объекты для героев мультика Том и Джерри. Так много, как только вспомнишь.
Пример:
Mouse jerryMouse = new Mouse(“Jerry”, 12 , 5), где 12 - высота в см, 5 - длина хвоста в см.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Mouse jerryMouse = new Mouse("Jerry", 12 , 5);

        //Напишите тут ваш код
        Dog buldogDog = new Dog ("Buldog", 6, 20);
        Dog smallDog = new Dog ("Smalldog", 1, 5);
        Cat catTomas = new Cat ("Tomas", 4, 5);
    }

    public static class Mouse
    {
        String name;
        int height;
        int tail;

        public Mouse(String name, int height, int tail)
        {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }

    //Напишите тут ваши классы
    public static class Dog
    {
        public String name;
        public int age;
        public int weight;

        public Dog (String name, int age, int weight)
        {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }
    }
    public static class Cat
    {
        public String name;
        public int age;
        public int weight;

        public Cat (String name, int age, int weight)
        {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }
    }
}
