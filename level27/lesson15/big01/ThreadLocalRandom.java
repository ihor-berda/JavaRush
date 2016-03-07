package com.javarush.test.level27.lesson15.big01;

import java.util.List;

public class ThreadLocalRandom implements Runnable {
    private int interval;
    private List<Tablet> tablets;

    public ThreadLocalRandom(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()) {
                int indexOfTablet = (int) (Math.random() * tablets.size());
                Tablet tablet = tablets.get(indexOfTablet);
                tablet.createTestOrder();

                Thread.sleep(interval);
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
