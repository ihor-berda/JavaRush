package com.javarush.test.level30.lesson08.home01;

/* Swap по-новому
В классе Pair реализуйте метод swap, который должен для x, y менять местами их значения.
Можно использовать только операции 1)исключающее или, 2) присваивание
Не оставляйте комментарии, не меняйте остальной код
*/
public class Solution {
    public static void main(String[] args) {
        Pair pair = new Pair(4, 5);
        System.out.println(pair);
        pair.swap();
        System.out.println(pair);
    }
}
