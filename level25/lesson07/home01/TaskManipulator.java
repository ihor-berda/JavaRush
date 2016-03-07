package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread current;
    @Override
    public void run() {
        try {
            while (!current.isInterrupted())
            {
                current.sleep(0);
                System.out.println(current.getName());
                current.sleep(90);
            }
        }
        catch (InterruptedException e) {}
    }

    @Override
    public void start(String threadName)
    {
        current = new Thread(this);
        current.setName(threadName);
        current.start();
    }

    @Override
    public void stop()
    {
        current.interrupt();
    }
}
