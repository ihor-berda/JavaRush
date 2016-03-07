package com.javarush.test.level23.lesson13.big01;

import java.util.ArrayList;

/**
 * Класс змея
 */
public class Snake
{
    //Направление движения змеи
    private SnakeDirection direction;
    //Состояние - жива змея или нет.
    private boolean isAlive;
    //Список кусочков змеи.
    private ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>();

    public Snake(int x, int y) // конструктор, принимает 2 координаты - х и у
    {
        sections = new ArrayList<SnakeSection>(); // создается список кусочков змеи
        sections.add(new SnakeSection(x, y)); // в список добавляется кусочек змеи с координатами х и у
        isAlive = true; // змея - жива
    }

    public boolean isAlive()
    {
        return isAlive;
    } // метод, который определяет, жива змея или нет

    public int getX()
    {
        return sections.get(0).getX();
    } // метод, который возвращает координату х головы змеи (0 - голова)

    public int getY()
    {
        return sections.get(0).getY();
    } // метод, который возвращает координату у головы змеи (0 - голова)

    public SnakeDirection getDirection()
    {
        return direction;
    } // метод, который возвращает направление змеи

    public void setDirection(SnakeDirection direction)
    {
        this.direction = direction;
    } // метод, который определяет направление змеи

    public ArrayList<SnakeSection> getSections()
    {
        return sections;
    } // метод, который возвращает список кусочков змеи

    /**
     * Метод перемещает змею на один ход.
     * Направление перемещения задано переменной direction.
     */
    public void move()
    {
        if (!isAlive) return;

        if (direction == SnakeDirection.UP) // движение вверх
            move(0, -1);
        else if (direction == SnakeDirection.RIGHT) // движение вправо
            move(1, 0);
        else if (direction == SnakeDirection.DOWN) // движение вниз
            move(0, 1);
        else if (direction == SnakeDirection.LEFT) // движение влево
            move(-1, 0);
    }

    /**
     * Метод перемещает змею в соседнюю клетку.
     * Кординаты клетки заданы относительно текущей головы с помощью переменных (dx, dy).
     */
    private void move(int dx, int dy)
    {
        //Создаем новую голову - новый "кусочек змеи".
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.getX() + dx, head.getY() + dy);

        //Проверяем - не вылезла ли голова за границу комнаты
        checkBorders(head);
        if (!isAlive) return;

        //Проверяем - не пересекает ли змея  саму себя
        checkBody(head);
        if (!isAlive) return;

        //Проверяем - не съела ли змея мышь.
        Mouse mouse = Room.game.getMouse();
        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) //съела
        {
            sections.add(0, head);                  //Добавили новую голову
            Room.game.eatMouse();                   //Хвот не удаляем, но создаем новую мышь.
        }
        else //просто движется
        {
            sections.add(0, head);                  //добавили новую голову
            sections.remove(sections.size() - 1);   //удалили последний элемент с хвоста
        }
    }

    /**
     *  Метод проверяет - находится ли новая голова в пределах комнаты
     */
    private void checkBorders(SnakeSection head)
    {
        if ((head.getX() < 0 || head.getX() >= Room.game.getWidth()) || head.getY() < 0 || head.getY() >= Room.game.getHeight())
        {
            isAlive = false;
        }
    }

    /**
     *  Метод проверяет - не совпадает ли голова с каким-нибудь участком тела змеи.
     */
    private void checkBody(SnakeSection head)
    {
        if (sections.contains(head))
        {
            isAlive = false;
        }
    }
}
