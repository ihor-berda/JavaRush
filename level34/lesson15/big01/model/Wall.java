package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by IGOR on 26.01.2016.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(128, 0, 0, 255));
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
