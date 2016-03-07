package com.javarush.test.level07.lesson09.task03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* Слово «именно»
1. Создай список из слов «мама», «мыла», «раму».
2. После каждого слова вставь в список строку, содержащую слово «именно».
3. Используя цикл for вывести результат на экран, каждый элемент списка с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        ArrayList<String> list = new ArrayList<String>();
        String s1 = "мама ";
        String s2 = "мыла ";
        String s3 = "раму ";
        String s4 = "именно";
        list.add (s1 + s4);
        list.add (s2 + s4);
        list.add (s3 + s4);
        for (int i = 0; i < list.size(); i++)
        System.out.println(list.get(i));
    }
}
