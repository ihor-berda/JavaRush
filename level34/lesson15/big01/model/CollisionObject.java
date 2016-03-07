package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by IGOR on 25.01.2016.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int newX = getX();
        int newY = getY();
        switch (direction) {
            case LEFT:
                newX = getX() - Model.FIELD_SELL_SIZE;
                return newX == gameObject.getX() && newY == gameObject.getY();
            case RIGHT:
                newX = getX() + Model.FIELD_SELL_SIZE;
                return newX == gameObject.getX() && newY == gameObject.getY();
            case UP:
                newY = getY() - Model.FIELD_SELL_SIZE;
                return newX == gameObject.getX() && newY == gameObject.getY();
            case DOWN:
                newY = getY() + Model.FIELD_SELL_SIZE;
                return newX == gameObject.getX() && newY == gameObject.getY();
            default: return false;
        }
    }
}
