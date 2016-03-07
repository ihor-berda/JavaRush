package com.javarush.test.level30.lesson08.task01;

/* Найдем число 2 в максимальной степени
Реализуйте логику метода maxPowerOf2,
который должен возвращать число 2 в максимальной степени, которое получается поместить в переданное число
Аргументом maxPowerOf2 может быть только положительное число
Используйте только операции: 1)побитовые сдвиги, 2) присваивание, 3) побитовое ИЛИ
Не оставляйте комментарии
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(maxPowerOf2(140_000));
        System.out.println(maxPowerOf2(1026));
        System.out.println(maxPowerOf2(17));
    }

    public static int maxPowerOf2(int x) {
        x |= (x >> 1);
        x |= (x >> 2);
        x |= (x >> 4);
        x |= (x >> 8);
        x |= (x >> 16);
        return (x & ~(x>>1));
    }
}
