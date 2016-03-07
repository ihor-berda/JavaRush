package com.javarush.test.level36.lesson06.task02;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* Поиск класса по описанию
Замените следующие слова на нужные:
1. ClassNameToBeReplaced - имя класса, потокобезопасный аналог ArrayList, в котором все операции изменения
(mutative operations) используют новую копию основного массива.

2. methodNameToBeReplaced - имя метода, который в текущий список 'list' добавляет те элементы переданной коллекции,
которые не содержатся в 'list'.

Не оставляйте комментированный код.
*/
public class Solution {
    public static void main(String... args) {    //it's correct line
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.remove("B");
        List<String> collection = Arrays.asList(new String[]{"B", "C", "D", "B"});

        list.addAllAbsent(collection);

        for (String string : list) {
            System.out.println(string);
        }
    }
}
