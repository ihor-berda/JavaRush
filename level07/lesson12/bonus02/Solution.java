package com.javarush.test.level07.lesson12.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Нужно добавить в программу новую функциональность
Задача:  Программа вводит строки, пока пользователь не введёт пустую строку (нажав enter). Потом она конвертирует строки в верхний регистр (Мама превращается в МАМА) и выводит их на экран.
Новая задача: Программа вводит строки, пока пользователь не введёт пустую строку (нажав enter). Потом программа строит новый список. Если в строке чётное число букв, строка удваивается, если нечётное – утраивается.
Пример ввода:
Кот
Коты
Я
Пример вывода:
Кот Кот Кот
Коты Коты
Я Я Я
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<String>();
        while (true)
        {
            String word = reader.readLine();
            if (word.isEmpty()) break;
            list.add(word);
        }

        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++)
        {
            String word = list.get(i);
            if (word.length() % 2 == 0)
            {
                words.add(word + " " + word);
            }
            else
                words.add(word + " " + word + " " + word);
        }

        for (int i = 0; i < words.size(); i++)
        {
            System.out.println(words.get(i));
        }
    }
}

