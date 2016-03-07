package com.javarush.test.level27.lesson09.home01;

/* Producer–consumer
В классе TransferObject расставьте вызов методов wait/notify/notifyAll,
чтобы обеспечить последовательное создание и получение объекта.
Ожидаемый вывод:
...
Put: M
Got: M
Put: N
Got: N
Put: K
Got: K
...
где M, N, K - числа
Метод main не участвует в тестировании
PS: всегда старайтесь использовать любой concurrent список вместо реализации wait/notify/notifyAll.
Однако понимать основные методы необходимо
*/
public class Solution {
    public static void main(String args[]) throws InterruptedException {
        TransferObject transferObject = new TransferObject();
        ProducerTask producerTask = new ProducerTask(transferObject);
        ConsumerTask consumerTask = new ConsumerTask(transferObject);

        Thread.sleep(50);
        producerTask.stop();
        consumerTask.stop();
    }
}
