package com.javarush.test.level37.lesson10.task01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* Давно забытый Array
Реализуйте логику метода getData так, чтобы main отработал без исключений
Остальной код не менять
*/
public class Solution {
    public static void main(String[] args) {
        List<Number> numbers = Arrays.<Number>asList(1, 2, 3);
        addDataToList(numbers, getData());
        System.out.println(numbers);
    }

    public static Number[] getData() {
        return new Number[]{};
    }

    public static void addDataToList(List<Number> list, Number... data) {
        for (Number number : data) {
            list.add(number);
        }
    }
}
