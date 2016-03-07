package com.javarush.test.level23.lesson13.big01;

public class Mouse
{
    private int x; // координата х мыши
    private int y; // координата у мыши

    public Mouse(int x, int y) // конструктор, принимает 2 координаты - х и у
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    } // метод, который возвращает координату х

    public int getY()
    {
        return y;
    } // метод, который возвращает координату у
}
