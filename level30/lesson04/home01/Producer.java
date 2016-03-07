package com.javarush.test.level30.lesson04.home01;


import java.util.concurrent.TransferQueue;

/**
 * Created by IGOR on 03.01.2016.
 */
public class Producer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        for (int i = 1; i <= 9; i++) {
            System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);
            queue.offer(new ShareItem("ShareItem-" + i, i));
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                return;
            }
            if (queue.hasWaitingConsumer()) {
                System.out.println("Consumer в ожидании!");
            }
        }
    }
}
