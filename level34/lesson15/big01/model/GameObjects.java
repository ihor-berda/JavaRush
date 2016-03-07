package com.javarush.test.level34.lesson15.big01.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IGOR on 26.01.2016.
 */
public class GameObjects
{
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Set<Wall> getWalls()
    {
        return walls;
    }

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> result = new HashSet<>();
        for (Wall wall : walls)
            result.add(wall);
        for (Box box : boxes)
            result.add(box);
        for (Home home : homes)
            result.add(home);
        result.add(player);
        return result;
    }
}
