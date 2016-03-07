package com.javarush.test.level29.lesson09.bonus01;

/* Argument and Value are generic types*/
public interface Computable<Argument, Value> {
    Value compute(Argument argument) throws InterruptedException;
}
