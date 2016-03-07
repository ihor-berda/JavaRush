package com.javarush.test.level29.lesson07.task01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Странные ошибки О_о
Исправьте 2 ошибки:
1) возникновение исключения
2) подвисание
Сделайте минимальные изменения.
*/
public class Solution {
    final int NUMBER_OF_THREADS = 3; //3 треда будет обрабатывать нашу очередь
    final int MAX_BATCH_SIZE = 100; //будем вытаскивать по 100 сообщений

    private Logger logger = Logger.getLogger(Solution.class.getName());
    private BlockingQueue messageQueue = new LinkedBlockingQueue();//тут будут храниться все сообщения

    private BlockingQueue fakeDataBase = new LinkedBlockingQueue();//тут будут храниться все сообщения*/

    public void startMessageCreating() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    messageQueue.add(String.valueOf(i));
                }
            }
        }.start();
    }

    public void startMessagePersisting() {
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            new Thread() {
                private final Collection batch = new ArrayList(MAX_BATCH_SIZE);
                {
                    setDaemon(true);
                }

                @Override
                public void run() {
                    while (true) {
                        try {
                            messageQueue.drainTo(batch, MAX_BATCH_SIZE);
                            persistData(batch);
                            batch.clear();
                            Thread.sleep(1);
                        } catch (Throwable e) {
                            logger.log(Level.SEVERE, "impossible to persist a batch", e);
                        }
                    }
                }
            }.start();
        }
    }

    private void persistData(Collection batch) {
        //представим, что тут мы коннектимся к базе данных, и сохраняем данные в нее
        //сохранение данных по 1 записи тратит много ресурсов, поэтому делают батчем (группой по несколько)
        fakeDataBase.addAll(batch);
    }

    private void printResults() {
        System.out.println();
        System.out.println("messageQueue size is " + messageQueue.size());
        System.out.println("fakeDataBase size is " + fakeDataBase.size());
    }

    public static void main(String[] args) throws InterruptedException {
        // статики во многих местах неуместны, поэтому помещаем все данные в поля класса,
        // затем создаем объект и вызываем его метод
        Solution solution = new Solution();

        solution.startMessageCreating();
        solution.startMessagePersisting();

        Thread.sleep(100);
        solution.printResults();

        Thread.sleep(100);
        solution.printResults();

        Thread.sleep(100);
        solution.printResults();

        Thread.sleep(500);
        solution.printResults();
    }
}
