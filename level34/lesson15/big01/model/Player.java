package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by IGOR on 25.01.2016.
 */
public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void move(int x, int y)
    {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.YELLOW);
        int newX = getX()-getWidth() / 2;
        int newY = getY()-getHeight() / 2;
        graphics.drawOval(newX, newY, getWidth(), getHeight());
        graphics.fillOval(newX, newY, getWidth(), getHeight());
    }
}
