package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    public Solution(int a) {}
    public Solution(int a, int b) {}
    public Solution(String s, int a, int b) {}
    protected Solution(Integer a) {}
    protected Solution(String s) {}
    protected Solution(Double d) {}
    private Solution(float f) {}
    private Solution(double d) {}
    private Solution(short s) {}
    Solution(Object o) {}
    Solution(float f, double d) {}
    Solution(String s, int i) {}
}

