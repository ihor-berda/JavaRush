package com.javarush.test.level26.lesson08.task03;

/* Распределение элементов по корзинам с собственным локом.
В синхронизированных блоках используйте нужный лок.
*/
public class Solution {
    private static final int NUMBER_LOCKS = 12; // число колов
    private final Node[] buckets; // массив корзин
    private final Object[] locks; // массив локов

    private static class Node {
        public Node next;
        public Object key; // ключ
        public Object value; // значение
    }

    public Solution(int numberBuckets) {
        buckets = new Node[numberBuckets]; // размер корзины может достигать до Integer MAX_VALUE
        locks = new Object[NUMBER_LOCKS]; // 12
        for (int i = 0; i < NUMBER_LOCKS; i++) {
            locks[i] = new Object(); // инициализация каждого лока новым объектом
        }
    }
    // Получанием хэш объекта путем вычесления остатка от деления хэшкода объекта на размер корзины.
    // Результат всегда является позитивным значением
    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) { // получаем объект
        int hash = hash(key); // получаем хэш
        synchronized (locks[hash % NUMBER_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next) { // от козина[хэш] пока не null
                if (m.key.equals(key)) return m.value; // если ключ равен объекту, возвращаем значение
            }
        }
        return null;
    }

    public void clear() { // очищаем объект
        for (int i = 0; i < buckets.length; i++) { // от 0 до размера корзины
            synchronized (locks[i % NUMBER_LOCKS]) { // синхронизируем -
                buckets[i] = null; // очищаем - присваеваем null ячейке i
            }
        }
    }
}
