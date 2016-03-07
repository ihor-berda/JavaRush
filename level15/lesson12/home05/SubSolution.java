package com.javarush.test.level15.lesson12.home05;

/**
 * Created by Igor on 09.08.2015.
 */
public class SubSolution extends Solution {
    public SubSolution(int a)
    {
        super(a);
    }

    public SubSolution(int a, int b)
    {
        super(a, b);
    }

    public SubSolution(String s, int a, int b)
    {
        super(s, a, b);
    }

    protected SubSolution(Integer a)
    {
        super(a);
    }

    protected SubSolution(String s)
    {
        super(s);
    }

    protected SubSolution(Double d)
    {
        super(d);
    }

    private SubSolution (float f) {
        super(f);
    }
    private SubSolution(double d) {
        super(d);
    }
    private SubSolution(short s) {
        super(s);
    }

    SubSolution(Object o)
    {
        super(o);
    }

    SubSolution(float f, double d)
    {
        super(f, d);
    }

    SubSolution(String s, int i)
    {
        super(s, i);
    }
}
