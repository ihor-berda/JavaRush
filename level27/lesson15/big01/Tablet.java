package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    private LinkedBlockingQueue<Order> queue;
    public final int number;
    public static java.util.logging.Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number)
    {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) { this.queue = queue; }

    public void createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            if (!order.isEmpty()) {
                queue.put(order);
                AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                advertisementManager.processVideos();
            }
        }
        catch (IOException e) { logger.log(Level.SEVERE, "Console is unavailable."); }
        catch (NoVideoAvailableException e) { logger.log(Level.INFO, "No video is available for the order " + order); }
        catch (InterruptedException e) {}
    }

    public void createTestOrder() {
        TestOrder testOrder = null;
        try {
            testOrder = new TestOrder(this);
            ConsoleHelper.writeMessage(testOrder.toString());
            if (!testOrder.isEmpty()) {
                queue.put(testOrder);
                AdvertisementManager advertisementManager = new AdvertisementManager(testOrder.getTotalCookingTime() * 60);
                advertisementManager.processVideos();
            }
        }
        catch (IOException e) { logger.log(Level.SEVERE, "Console is unavailable."); }
        catch (NoVideoAvailableException e) { logger.log(Level.INFO, "No video is available for the order " + testOrder); }
        catch (InterruptedException e) {}
    }

    @Override
    public String toString() { return "Tablet{number=" + number + "}"; }
}