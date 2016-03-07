package com.javarush.test.level25.lesson05.home01;

/**
 * Created by IGOR on 28.11.2015.
 */
public class LoggingStateThread extends Thread
{
    Thread target;
    public LoggingStateThread(Thread target)
    {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run()
    {
        State state = this.target.getState();
        System.out.println(state);
        while (state != State.TERMINATED) {
            if (state != this.target.getState()) {
                state = this.target.getState();
                System.out.println(state);
            }
        }
    }
}
