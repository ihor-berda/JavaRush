package com.javarush.test.level27.lesson15.big01.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);
    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        String dishes = "";
        for (Dish dish : Dish.values()){
            dishes += dish + ", ";
        }
        return (dishes.length() != 0) ? dishes.substring(0, dishes.lastIndexOf(",")) : "";
    }
}