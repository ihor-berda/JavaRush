package com.javarush.test.level27.lesson04.home01;

/* Модификаторы и дедлоки
Расставьте модификаторы так, чтобы при работе с этим кодом появился дедлок
*/
public class Solution {
    public synchronized Object getData() {
        return new ThreadDeadlock().getData();
    }
}
