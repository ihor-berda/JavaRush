package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/* Экономим время
1. Создайте Producer и Consumer (См. комментарий к методу main)
2. Создайте методы toString, equals и hashCode в классе ShareItem. Для этого в теле класса ShareItem выполни:
2.1. Alt+Enter -> toString() -> Enter
2.2. Alt+Enter -> equals() and hashCode() -> click all 'Next'-s
3. В Producer и Consumer реализуйте метод run так, чтобы вызов метода interrupt прерывал работу consumer и producer трэдов

4. Реализация метода run для Producer:
4.1. Используя метод offer добавить в очередь 9 ShareItem-ов с такими параметрами: ("ShareItem-N", N), где N - номер элемента от 1 до 9
4.2. Перед каждым добавлением вывести фразу "Элемент 'ShareItem-N' добавлен". Используйте System.out.format
4.3. Усыпить трэд на 0.1 секунды
4.4. Если у очереди есть Consumer, не занятый работой, то вывести фразу "Consumer в ожидании!".
Просмотрите методы интерфейса TransferQueue, там есть нужный метод.

5. Реализация метода run для Consumer:
5.1. Усыпить трэд на 0.5 секунды
5.2. В бесконечном цикле заберите элемент из очереди методом take и выведите в консоль "Processing item.toString()".

6. Сверьте вывод с файлом output.txt
7. Стек-трейс не выводите в консоль
*/
public class Solution {
    /*
    1. Создайте класс Producer. Для этого на красном имени класса нажмите Alt+Enter -> Create Class ...
    2. Станьте на имени аргумента в конструкторе (queue) и нажмите Alt+Enter -> Create Field for Parameter 'queue' -> Enter -> Enter
    3. Станьте на подчеркнутой строке - описании класса. Далее Alt+Enter -> Implement Methods -> Enter
    4. Проделайте п.1-3 для класса Consumer
     */

    public static void main(String[] args) throws InterruptedException {
        TransferQueue<ShareItem> queue = new LinkedTransferQueue<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();

        Thread.sleep(1500);

        producer.interrupt();
        consumer.interrupt();
    }

}
