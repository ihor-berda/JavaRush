package com.javarush.test.level05.lesson09.task04;

/* Создать класс Circle
Создать класс (Circle) круг, с тремя конструкторами:
- centerX, centerY, radius
- centerX, centerY, radius, width
- centerX, centerY, radius, width, color
*/

public class Circle
{
    //Напишите тут ваш код
    public int circlecenterX;
    public int circlecenterY;
    public int circleradius;
    public int cirlcewidth;
    public int circlecolor;

    public Circle (int centerX, int centerY, int radius)
    {
        this.circlecenterX = centerX;
        this.circlecenterY = centerY;
        this.circleradius = radius;
    }
    public Circle (int centerX, int centerY, int radius, int wigth)
    {
        this.circlecenterX = centerX;
        this.circlecenterY = centerY;
        this.circleradius = radius;
        this.cirlcewidth = wigth;
    }
    public Circle (int centerX, int centerY, int radius, int wigth, int color)
    {
        this.circlecenterX = centerX;
        this.circlecenterY = centerY;
        this.circleradius = radius;
        this.cirlcewidth = wigth;
        this.circlecolor = color;
    }

}
