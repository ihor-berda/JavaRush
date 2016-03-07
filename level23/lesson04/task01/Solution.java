package com.javarush.test.level23.lesson04.task01;

/* Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] array = new Solution[2];
        Solution solution = new Solution();
        array[0] = new Solution();
        array[0].innerClasses[0] = solution.new InnerClass();
        array[0].innerClasses[1] = solution.new InnerClass();
        array[1] = new Solution();
        array[1].innerClasses[0] = solution.new InnerClass();
        array[1].innerClasses[1] = solution.new InnerClass();
        return array;
    }
}
