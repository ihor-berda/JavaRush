package com.javarush.test.level26.lesson02.task01;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        final float medium;
        if (array.length % 2 == 0)
            medium = (array[array.length / 2 - 1] + array[array.length / 2]) / 2f;
        else
            medium = array[array.length / 2];
        Arrays.sort(array, new Comparator<Integer>()
        {

            @Override
            public int compare(Integer o1, Integer o2)
            {
                int result = (int) (Math.abs(o1 - medium) - Math.abs(o2 - medium));
                return result == 0 ? o1 - o2 : result;
            }
        });
        return array;
    }
}
