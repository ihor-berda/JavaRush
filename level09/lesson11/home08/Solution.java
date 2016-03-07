package com.javarush.test.level09.lesson11.home08;

import java.util.ArrayList;

/* Список из массивов чисел
Создать список, элементами которого будут массивы чисел. Добавить в список пять объектов–массивов длиной 5, 2, 4, 7, 0 соответственно. Заполнить массивы любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList()
    {
        //напишите тут ваш код
        int[] list1 = new int [5];
        int[] list2 = new int [2];
        int[] list3 = new int [4];
        int[] list4 = new int [7];
        int[] list5 = new int [0];
        ArrayList<int[]>createdList = new ArrayList<int[]>();
        createdList.add(list1);
        createdList.add(list2);
        createdList.add(list3);
        createdList.add(list4);
        createdList.add(list5);
        for (int i = 0; i < createdList.size(); i++) {
            for (int j = 0; j < createdList.get(i).length; j++) {
                createdList.get(i)[j] = j;
            }
        }
        return createdList;
    }

    public static void printList(ArrayList<int[]> list)
    {
        for (int[] array: list )
        {
            for (int x: array)
            {
                System.out.println(x);
            }
        }
    }
}
