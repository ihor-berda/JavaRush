package com.javarush.test.level30.lesson02.home01;

public class Number {
    private NumerationSystem numerationSystem;
    private String digit;

    public Number(NumerationSystem numerationSystem, String digit) {
        this.numerationSystem = numerationSystem;
        this.digit = digit;
    }

    public NumerationSystem getNumerationSystem() {
        return numerationSystem;
    }

    public String getDigit() {
        return digit;
    }

    @Override
    public String toString() {
        return "Number{" +
                "numerationSystem=" + numerationSystem +
                ", digit='" + digit + '\'' +
                '}';
    }
}
