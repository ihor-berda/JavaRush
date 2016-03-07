package com.javarush.test.level16.lesson03.task03;

import java.util.ArrayList;
import java.util.List;

/* Список и нити
В методе main добавить в статический объект list 5 нитей SpecialThread - различных объектов.
*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    public static void main(String[] args) {
        //Add your code here - добавьте свой код тут
        SpecialThread a = new SpecialThread();
        Thread thread1 = new Thread(a);
        list.add(thread1);
        SpecialThread b = new SpecialThread();
        Thread thread2 = new Thread(b);
        list.add(thread2);
        SpecialThread c = new SpecialThread();
        Thread thread3 = new Thread(c);
        list.add(thread3);
        SpecialThread d = new SpecialThread();
        Thread thread4 = new Thread(d);
        list.add(thread4);
        SpecialThread e = new SpecialThread();
        Thread thread5 = new Thread(e);
        list.add(thread5);
    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's run method inside SpecialThread");
        }
    }
}
