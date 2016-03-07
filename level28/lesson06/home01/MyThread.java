package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IGOR on 23.12.2015.
 */
public class MyThread extends Thread {
    public static AtomicInteger atomicInteger = new AtomicInteger(1);
    public MyThread(Runnable target, String name) {
        super(target, name);
        if (atomicInteger.get() < 10) {
            setPriority(atomicInteger.getAndIncrement());
        }
        else {
            setPriority(atomicInteger.get());
            atomicInteger.set(1);
        }
    }

    public MyThread(Runnable target) {
        super(target);
        if (atomicInteger.get() < 10) {
            setPriority(atomicInteger.getAndIncrement());
        }
        else {
            setPriority(atomicInteger.get());
            atomicInteger.set(1);
        }
    }

    public MyThread(String name) {
        super(name);
        if (atomicInteger.get() < 10) {
            setPriority(atomicInteger.getAndIncrement());
        }
        else {
            setPriority(atomicInteger.get());
            atomicInteger.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        int max = group.getMaxPriority();
        int temp = 0;
        if (atomicInteger.get() < 10) {
            temp = atomicInteger.get();
            temp = atomicInteger.getAndIncrement();
            if (temp > max) {
                setPriority(max);
            }
            else setPriority(temp);
        }
        else {
            temp = atomicInteger.get();
            if (temp > max) {
                setPriority(max);
            }
            else setPriority(temp);
            atomicInteger.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        int max = group.getMaxPriority();
        int temp = 0;
        if (atomicInteger.get() < 10) {
            temp = atomicInteger.get();
            temp = atomicInteger.getAndIncrement();
            if (temp > max) {
                setPriority(max);
            }
            else setPriority(temp);
        }
        else {
            temp = atomicInteger.get();
            if (temp > max) {
                setPriority(max);
            }
            else setPriority(temp);
            atomicInteger.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        int max = group.getMaxPriority();
        int temp = 0;
        if (atomicInteger.get() < 10) {
            temp = atomicInteger.get();
            temp = atomicInteger.getAndIncrement();
            if (temp > max) {
                setPriority(max);
            }
            else setPriority(temp);
        }
        else {
            temp = atomicInteger.get();
            if (temp > max) {
                setPriority(max);
            }
            else setPriority(temp);
            atomicInteger.set(1);
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        int max = group.getMaxPriority();
        int temp = 0;
        if (atomicInteger.get() < 10) {
            temp = atomicInteger.get();
            temp = atomicInteger.getAndIncrement();
            if (temp > max) {
                setPriority(max);
            }
            else setPriority(temp);
        }
        else {
            temp = atomicInteger.get();
            if (temp > max) {
                setPriority(max);
            }
            else setPriority(temp);
            atomicInteger.set(1);
        }
    }

    public MyThread() {
        if (atomicInteger.get() < 10) {
            setPriority(atomicInteger.getAndIncrement());
        }
        else {
            setPriority(atomicInteger.get());
            atomicInteger.set(1);
        }
    }
}
