package com.javarush.test.level05.lesson07.task04;

/* Создать класс Circle
Создать класс (Circle) круг, с тремя инициализаторами:
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

    public void initialize (int centerX, int centerY, int radius)
    {
        this.circlecenterX = centerX;
        this.circlecenterY = centerY;
        this.circleradius = radius;
    }
    public void initialize (int centerX, int centerY, int radius, int wigth)
    {
        this.circlecenterX = centerX;
        this.circlecenterY = centerY;
        this.circleradius = radius;
        this.cirlcewidth = wigth;
    }
    public void initialize (int centerX, int centerY, int radius, int wigth, int color)
    {
        this.circlecenterX = centerX;
        this.circlecenterY = centerY;
        this.circleradius = radius;
        this.cirlcewidth = wigth;
        this.circlecolor = color;
    }




}
