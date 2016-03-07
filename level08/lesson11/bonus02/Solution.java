package com.javarush.test.level08.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Нужно добавить в программу новую функциональность
Задача: Программа определяет, какая семья (фамилию) живёт в доме с указанным номером.
Новая задача: Программа должна работать не с номерами домов, а с городами:
Пример ввода:
Москва
Ивановы
Киев
Петровы
Лондон
Абрамовичи

Лондон

Пример вывода:
Абрамовичи
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //list of addresses
        Map<String, String> adress = new HashMap<String, String>();
        while (true) {
            String townName = reader.readLine();
            if (townName.isEmpty()) break;
            String familyName = reader.readLine();
            adress.put(townName, familyName);
        }

        //read home number
        String currentTown = reader.readLine();
        for(Map.Entry<String, String> pair : adress.entrySet()) {
            if (currentTown.equals(pair.getKey()))
                System.out.println(pair.getValue());
            else continue;
        }
    }
}
