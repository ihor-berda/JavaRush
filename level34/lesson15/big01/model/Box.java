package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by IGOR on 25.01.2016.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
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
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        int newX = getX() - getWidth() / 2;
        int newY = getY() - getHeight() / 2;
        graphics.drawRect(newX, newY, getWidth(), getHeight());
        graphics.fillRect(newX, newY, getWidth(), getHeight());

    }
}
