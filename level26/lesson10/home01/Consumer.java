package com.javarush.test.level26.lesson10.home01;

import java.util.concurrent.BlockingQueue;

/**
 * Created by IGOR on 03.12.2015.
 */
public class Consumer implements Runnable
{
    protected BlockingQueue queue;

    public Consumer(BlockingQueue queue)
    {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                System.out.println(queue.take());
            }
        } catch (InterruptedException e) {

        }
    }
}
