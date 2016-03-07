package com.javarush.test.level23.lesson13.big01;

public class SnakeSection
{
    private int x;
    private int y;


    public SnakeSection(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SnakeSection solution = (SnakeSection) obj;

        if (x != solution.x) return false;
        return y == solution.y;
    }

}
