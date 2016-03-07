package com.javarush.test.level17.lesson10.bonus03;

/* Ресторан
1.Разберись, что делает программа. Официант почему-то не относит приготовленные блюда назад к столам :(
2.Исправь ошибку.
Подсказка: это одна строчка
*/

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Waiter waiterTarget = new Waiter();
        Thread waiter = new Thread(waiterTarget);
        threads.add(waiter);

        Cook cookTarget = new Cook();
        Thread cook = new Thread(cookTarget);
        threads.add(cook);

        waiter.start();
        cook.start();

        Thread.sleep(2000);
        cookTarget.continueWorking = false;
        Thread.sleep(500);
        waiterTarget.continueWorking = false;
    }
}
