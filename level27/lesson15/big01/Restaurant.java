package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final static int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(QUEUE);
        Cook cook2 = new Cook("Amigo2");
        cook2.setQueue(QUEUE);
        Waitor waitor = new Waitor();
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);
        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(QUEUE);
            tabletList.add(tablet);
        }
        ThreadLocalRandom generatorTask = new ThreadLocalRandom(tabletList, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(generatorTask);
        Thread cookThread1 = new Thread(cook1);
        Thread cookThread2 = new Thread(cook2);
        thread.start();
        cookThread1.start();
        cookThread2.start();
        try { Thread.sleep(1000); }
        catch (InterruptedException e) {}
        thread.interrupt();
        cookThread1.interrupt();
        cookThread2.interrupt();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}


