package com.javarush.test.level12.lesson12.bonus03;

/* Задача по алгоритмам
Написать метод, который возвращает минимальное число в массиве и его позицию (индекс).
*/

import java.util.Arrays;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int[] data = new int[]{1, 2, 3, 5, -2, -8, 0, 77, 5, 5};

        Pair<Integer, Integer> result = getMinimumAndIndex(data);

        System.out.println("Minimum is " + result.x);
        System.out.println("Index of minimum element is " + result.y);
    }

    public static Pair<Integer, Integer> getMinimumAndIndex(int[] array)
    {
        if (array == null || array.length == 0)
        {
            return new Pair<Integer, Integer>(null, null);
        }
        int min;
        min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min)
            min = array[i];
        }
        Integer[] data = new Integer[]{1, 2, 3, 5, -2, -8, 0, 77, 5, 5};
        Integer[] array2 = Arrays.copyOf(data, data.length);
        Arrays.sort(array2);

        return new Pair<Integer, Integer>(min, Arrays.asList(data).indexOf(array2[0]));
    }


    public static class Pair<X, Y>
    {
        public X x;
        public Y y;

        public Pair(X x, Y y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
