package com.javarush.test.level32.lesson08.bonus01;

import com.javarush.test.level32.lesson08.bonus01.Big;
import com.javarush.test.level32.lesson08.bonus01.Item;
import com.javarush.test.level32.lesson08.bonus01.Small;

import java.lang.reflect.Proxy;


/* Дженерики для создания прокси-объекта
В классе Solution создайте публичный метод getProxy
1) Метод getProxy должен возвращать прокси для любого интерфейса, который наследуется от Item
2) getProxy должен иметь два параметра. Первый - класс возвращаемого типа, второй - классы дополнительных интерфейсов.
3) Используйте ItemInvocationHandler для создания прокси
Метод main не участвует в тестировании
*/
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Big.class));                         //true true false т.к. Big наследуется от Item
    }


    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }

    public <T extends Item> T getProxy(Class<T> itemClass, Class... classes) {
        Class[] arr = new Class[classes.length + 1];
        arr[0] = itemClass;
        System.arraycopy(classes, 0, arr, 1, classes.length);
        return (T) Proxy.newProxyInstance(itemClass.getClassLoader(), arr, new ItemInvocationHandler());
    }
}
