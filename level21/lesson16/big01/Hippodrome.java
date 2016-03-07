package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by IGOR on 14.11.2015.
 */
public class Hippodrome
{
    public static ArrayList<Horse> horses = new ArrayList<Horse>();
    public static Hippodrome game;
    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public static void main(String[] args) throws InterruptedException
    {
        game = new Hippodrome();
        game.horses.add(new Horse("Horse1", 3, 0));
        game.horses.add(new Horse("Horse2", 3, 0));
        game.horses.add(new Horse("Horse3", 3, 0));
        game.run();
        game.printWinner();
    }
    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }
    public void move() {
        for (Horse h : horses) {
            h.move();
        }
    }
    public void print() {
        for (Horse h : horses) {
            h.print();
        }
        System.out.println();
        System.out.println();
    }
    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (Horse h : getHorses()) {
            if (h.getDistance() > winner.getDistance()) {
                winner = h;
            }
        }
        return winner;
    }
    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
