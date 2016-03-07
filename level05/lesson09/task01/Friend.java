package com.javarush.test.level05.lesson09.task01;

/* Создать класс Friend
Создать класс Friend (друг) с тремя конструкторами:
- Имя
- Имя, возраст
- Имя, возраст, пол
*/

public class Friend
{
    //Напишите тут ваш код
    public String friendname = null;
    public int friendage;
    public String friendsex = null;

    public Friend (String name)
    {
        this.friendname = name;
    }
    public Friend (String name, int age)
    {
        this.friendname = name;
        this.friendage = age;
    }
    public Friend (String name, int age, String sex)
    {
        this.friendname = name;
        this.friendage = age;
        this.friendsex = sex;
    }
}