package com.javarush.test.level21.lesson16.big01;

/**
 * Created by IGOR on 14.11.2015.
 */
public class Horse
{
    public String name;
    public double speed;
    public double distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }
    public void move() {
        int num = (int) Math.random();
        speed = speed * num;
        distance += speed;
    }
    public void print() {
        int dis = (int) distance;
        String tochka = "";
        for (int i = 0; i < dis; i++) {
            tochka = tochka + ".";
        }
        System.out.println(tochka + name);
    }
}
