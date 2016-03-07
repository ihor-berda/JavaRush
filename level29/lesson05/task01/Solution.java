package com.javarush.test.level29.lesson05.task01;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;

/* И еще раз рефакторинг
1. Исправить код в соответствии с Naming and Code Convention (Shift+F6 для рефакторинга)
2. Просмотрите методы класса ConcurrentMap.
3. В строке "String previousEntry = null;" у concurrentMap вызовите метод,
который вставит пару (randomInt, text) только для ключа, которого нет в concurrentMap.
Метод должен возвращать предыдущее значение либо null для новой пары.
*/
public class Solution {
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static void main(String[] args) {
        ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(getRunnable(i, concurrentMap)).start();
        }
        sleepASecond();
    }

    private static void sleepASecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Runnable getRunnable(final int i, final ConcurrentMap<Integer, String> concurrentMap) {
        return new Runnable() {
            @Override
            public void run() {
                final String name = "Thread #" + i;
                int randomInt = RANDOM.nextInt(20);
                String text = name + " вставил запись для " + randomInt;

                String previousEntry = concurrentMap.putIfAbsent(randomInt, text);

                if (previousEntry != null) {
                    System.out.println(name + " хочет обновить " + randomInt + ", однако уже " + previousEntry);
                } else {
                    System.out.println(text);
                }
            }
        };
    }
}
