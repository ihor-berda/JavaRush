package com.javarush.test.level26.lesson15.big01;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        if (i == 0) throw new IllegalArgumentException();
        else if (i == 1) return Operation.INFO;
        else if (i == 2) return Operation.DEPOSIT;
        else if (i == 3) return Operation.WITHDRAW;
        else if (i == 4) return Operation.EXIT;
        else throw new IllegalArgumentException();
    }
}

