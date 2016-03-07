package com.javarush.test.level28.lesson08.task02;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* Знакомство с ThreadPoolExecutor
1. В методе main создай очередь LinkedBlockingQueue<Runnable>
2. В цикле добавь в очередь 10 тасок Runnable.
3. У каждой таски в методе run вызови метод doExpensiveOperation с порядковым номером таски начиная с 1, см. пример вывода
4. Создай объект ThreadPoolExecutor со следующими параметрами:
- основное количество трэдов (ядро) = 3
- максимальное количество трэдов = 5
- время удержания трэда живым после завершения работы = 1000
- тайм-юнит - миллисекунды
- созданная в п.1. очередь с тасками
5. Запусти все трэды, которые входят в основное кол-во трэдов - ядро), используй метод prestartAllCoreThreads
6. Запрети добавление новых тасок на исполнение в пул (метод shutdown)
7. Дай экзэкьютору 5 секунд на завершение всех тасок (метод awaitTermination и параметр TimeUnit.SECONDS)
Не должно быть комментариев кроме приведенного output example
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        //Add your code here
        final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        for (int i = 1; i <= 10; i++) {
            final int inc = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(inc);
                }
            };
            queue.put(runnable);
        }
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, queue);
        tpe.prestartAllCoreThreads();
        tpe.shutdown();
        tpe.awaitTermination(5, TimeUnit.SECONDS);
        /* output example
pool-1-thread-2, localId=2
pool-1-thread-3, localId=3
pool-1-thread-1, localId=1
pool-1-thread-3, localId=5
pool-1-thread-2, localId=4
pool-1-thread-3, localId=7
pool-1-thread-1, localId=6
pool-1-thread-3, localId=9
pool-1-thread-2, localId=8
pool-1-thread-1, localId=10
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
