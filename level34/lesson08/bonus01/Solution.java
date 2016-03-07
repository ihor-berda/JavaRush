package com.javarush.test.level34.lesson08.bonus01;

/* Кэширование
Класс Cache - универсальный параметризированный класс для кеширования объектов.
Он работает с классами(дженерик тип Т), у которых обязан быть:
а) публичный конструктор с одним параметром типа K
б) метод K getKey() с любым модификатором доступа

Задание:
1. Выберите правильный тип для поля cache. Map<K, V> cache должен хранить ключи, на которые есть активные ссылки.
Если нет активных ссылок на ключи, то они вместе со значениями должны автоматически удаляться из cache.
2. Реализуйте логику метода getByKey:
2.1. Верните объект из cache для ключа key
2.2. Если объекта не существует в кэше, то добавьте в кэш новый экземпляр используя рефлекшн, см. а)
3. Реализуйте логику метода put:
3.1. Используя рефлекшн получите ссылку на метод, описанный в п.б)
3.2. Используя рефлекшн разрешите к нему доступ
3.3. Используя рефлекшн вызовите метод getKey у объекта obj, таким образом Вы получите ключ key
3.4. Добавьте в кэш пару <key, obj>
3.5. Верните true, если метод отработал корректно, false в противном случае. Исключения игнорируйте.
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        SomeKey someKey = new SomeKey();
        someKey.name = "test";

        SomeKey someKeyNew = new SomeKey();
        someKeyNew.name = "testNew";

        SomeValue value = new SomeValue(someKey);

        Cache<SomeKey, SomeValue> cache = new Cache<>();
        cache.put(value);

        SomeValue valueFromCacheExisted = cache.getByKey(someKey, SomeValue.class);
        System.out.println(valueFromCacheExisted);

        SomeValue valueFromCacheNew = cache.getByKey(someKeyNew, SomeValue.class);
        System.out.println(valueFromCacheNew);

        System.out.println(cache.size());
        /* expected output:
        SomeValue{myKey=SomeKey{name='test'}}
        SomeValue{myKey=SomeKey{name='testNew'}}
        2
         */
    }

    public static class SomeKey {
        String name;

        @Override
        public String toString() {
            return "SomeKey{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class SomeValue {
        private SomeKey myKey;

        public SomeValue() {
        }

        public SomeValue(SomeKey myKey) {        //use this constructor
            this.myKey = myKey;
        }

        private SomeKey getKey() {
            return myKey;
        }

        @Override
        public String toString() {
            return "SomeValue{" +
                    "myKey=" + myKey +
                    '}';
        }
    }
}
