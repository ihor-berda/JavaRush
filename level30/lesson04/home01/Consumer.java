package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by IGOR on 03.01.2016.
 */
public class Consumer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
            }
            for (; ; )
            {
                try
                {
                    System.out.println("Processing " + queue.take().toString());
                }
                catch (InterruptedException e)
                {
                    return;
                }
            }
    }
}
