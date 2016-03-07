package com.javarush.test.level36.lesson10.task01;

import java.util.concurrent.DelayQueue;

/* Найти класс по описанию
1. Реализует интерфейс Queue
2. Используется при работе с трэдами
3. Из этой очереди элементы могут быть взяты только тогда, когда они заэкспарятся, их время задержки истекло
4. Головой очереди является элемент, который заэкспарился раньше всех
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        return DelayQueue.class;
    }
}
