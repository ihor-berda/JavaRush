package com.javarush.test.level27.lesson09.task01;

import java.util.concurrent.CountDownLatch;

/* CountDownLatch
Дана стандартная реализация методологии wait-notify.
Почитайте про CountDownLatch и перепишите тело метода someMethod используя поле latch.
Весь лишний код удалите из класса.
*/
public abstract class Solution {
    private final CountDownLatch latch = new CountDownLatch(1);

    public void someMethod() throws InterruptedException {
        latch.await();
        retrieveValue();
        latch.countDown();
    }

    abstract void retrieveValue();
}
