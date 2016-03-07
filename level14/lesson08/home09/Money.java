package com.javarush.test.level14.lesson08.home09;

public abstract class Money
{
    public Money(double amount)
    {
        this.amount = amount;
    }

    private double amount;
    public double getAmount() {
        return amount;
    }


    public abstract String getCurrencyName();
}

