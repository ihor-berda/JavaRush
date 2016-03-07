package com.javarush.test.level28.lesson06.task01;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/* Magic class
В пакете java.util.concurrent найдите такую реализацию List, у которой
1. итератор не содержит какие-либо данные, вставленные в список после создания итератора
2. внутри данные хранятся в виде массива
3. итератор гарантированно не бросает ConcurrentModificationException
4. допускаются все элементы включая null
5. Исправьте строку List<String> list = null ... в методе main
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Solution solution = new Solution();
        List<String> list = new CopyOnWriteArrayList<>()

        /* create object of this magic class here*/;

        solution.startUpdatingThread(list);
        solution.copyOnWriteSolution(list);
        solution.stopUpdatingThread();

        /* The output example
size = 0
Element :s781859336
Element :s1453499757
Element :s911312405
Element :s-877520242
Element :s-1636258097
size = 5

Element2 :s781859336
Element2 :s1453499757
Element2 :s911312405
Element2 :s-877520242
Element2 :s-1636258097
Element2 :s-1739827856
Element2 :s-938954654
Element2 :s925086217
size = 8
         */
    }

    public void copyOnWriteSolution(List<String> list) throws InterruptedException {
        System.out.println("size = " + list.size());
        System.out.println();
        Thread.sleep(20);
        for (String element : list) {   //создает новую копию на момент создания итератора
            System.out.println("Element :" + element);
        }
        System.out.println("size = " + list.size());
        System.out.println();
        Thread.sleep(20);

        for (String element : list) {  //создает новую копию на момент создания итератора
            System.out.println("Element2 :" + element);
        }
        System.out.println("size = " + list.size());
        stopUpdatingThread();
    }

    public void stopUpdatingThread() throws InterruptedException {
        t.stop();
        t.join();
    }

    private Thread t;

    private void startUpdatingThread(final List<String> list) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    list.add("s" + ThreadLocalRandom.current().nextInt());
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        t.start();
    }
}
