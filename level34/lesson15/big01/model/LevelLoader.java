package com.javarush.test.level34.lesson15.big01.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IGOR on 26.01.2016.
 */
public class LevelLoader
{
    private Path levels;
    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
//        int x0 = Model.FIELD_SELL_SIZE / 2;
//        int y0 = Model.FIELD_SELL_SIZE / 2;
//        Player player = new Player(x0, y0);
//        Set<Home> homes = new HashSet<>();
//        homes.add(new Home(x0 + Model.FIELD_SELL_SIZE, y0));
//        Set<Box> boxes = new HashSet<>();
//        boxes.add(new Box(x0, y0 + Model.FIELD_SELL_SIZE));
//        Set<Wall> walls = new HashSet<>();
//        walls.add(new Wall(x0 + Model.FIELD_SELL_SIZE, y0 + Model.FIELD_SELL_SIZE));
//        walls.add(new Wall(x0, y0 + Model.FIELD_SELL_SIZE * 2));
//        return new GameObjects(walls, boxes, homes, player);

        int currentLevel = (level % 60 == 0) ? 60 : level % 60;
        String stringLevel = "Maze: " + currentLevel;
        List<String> levelInfo = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            String line = "";
            while (!line.equals(stringLevel)) { line = reader.readLine(); }
            for (int i = 0; i < 7; i++) { line = reader.readLine(); }
            while (!"".equals(line)) {
                levelInfo.add(line);
                line = reader.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        for (int i = 0; i < levelInfo.size(); i++) {
            String wight = levelInfo.get(i);
            for (int j = 0; j < wight.length(); j++) {
                int x = (j == 0) ? Model.FIELD_SELL_SIZE / 2 : (Model.FIELD_SELL_SIZE / 2) + j * Model.FIELD_SELL_SIZE;
                int y = (i == 0) ? Model.FIELD_SELL_SIZE / 2 : (Model.FIELD_SELL_SIZE / 2) + i * Model.FIELD_SELL_SIZE;
                char symbol = wight.charAt(j);
                switch (symbol) {
                    case 'X' :
                        walls.add(new Wall(x, y));
                        break;
                    case '*' :
                        boxes.add(new Box(x, y));
                        break;
                    case '.' :
                        homes.add(new Home(x, y));
                        break;
                    case '&' :
                        boxes.add(new Box(x, y));
                        homes.add(new Home(x, y));
                        break;
                    case '@' :
                        player = new Player(x, y);
                        break;
                }
            }
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
