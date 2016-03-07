package com.javarush.test.level29.lesson09.bonus01;

public class Square implements Computable<Integer, Integer> {
    @Override
    public Integer compute(Integer integer) throws InterruptedException {
        int val = integer.intValue();
        return val * val;
    }
}
