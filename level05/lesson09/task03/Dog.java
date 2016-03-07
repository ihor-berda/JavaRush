package com.javarush.test.level05.lesson09.task03;

/* Создать класс Dog
Создать класс Dog (собака) с тремя конструкторами:
- Имя
- Имя, рост
- Имя, рост, цвет
*/

public class Dog
{
    //Напишите тут ваш код
    public String dogname = null;
    public int dogheight;
    public int dogcolor;

    public Dog (String name)
    {
        this.dogname = name;
    }
    public Dog (String name, int height)
    {
        this.dogname = name;
        this.dogheight = height;
    }
    public Dog (String name, int height, int color)
    {
        this.dogname = name;
        this.dogheight = height;
        this.dogcolor = color;
    }
}
