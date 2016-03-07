package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private LinkedBlockingQueue<Order> queue;
    private String name;
    private volatile boolean caught = false;

    public void setQueue(LinkedBlockingQueue<Order> queue) { this.queue = queue; }

    public Cook(String name) { this.name = name; }

    public void startCookingOrder(Order order) {
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %dmin", order, order.getTotalCookingTime()));
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        setChanged();
        notifyObservers(order);
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        }
        catch (InterruptedException e) {
            caught = true;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                startCookingOrder(queue.take());
            }
            catch (InterruptedException e) {}
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                caught = true;
            }
            if (caught && queue.isEmpty()) break;
        }
    }

    @Override
    public String toString()
    {
        return name;
    }
}