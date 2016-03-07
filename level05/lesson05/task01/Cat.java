package com.javarush.test.level05.lesson05.task01;

/* Создать класс Cat
Создать класс Cat. У кота должно быть имя (name, String), возраст (age, int), вес (weight, int), сила (strength, int).
*/

public class Cat
{
    //Напишите тут ваш код
    public String name;
    public int age;
    public int weight;
    public int strength;
    public static void main (String args[])
    {
        Cat catVaska = new Cat();
        catVaska.name = "name";
        catVaska.age = 4;
        catVaska.weight = 10;
        catVaska.strength = 5;
    }

}
