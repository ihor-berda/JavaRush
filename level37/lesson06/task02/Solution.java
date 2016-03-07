package com.javarush.test.level37.lesson06.task02;

import java.util.HashMap;
import java.util.Map;

/* Фикс бага
В Вашем большом проекте повсеместно используется метод getExpectedMap.
В какой-то момент тестеры обнаружили, что метод getExpectedMap выбрасывает NullPointerException.
Ожидаемое поведение описано в комментарии метода main.
Найдите другую стандартную реализацию интерфейса Map, верните объект этого типа в getExpectedMap вместо TreeMap.
Метод main должен отработать без исключений.
Метод main не менять.
*/
public class Solution {
    public static void main(String[] args) {
        Map expectedMap = getExpectedMap();

        System.out.println("********* Add obj with NULL key *********");
        expectedMap.put(null, "text 1");
        System.out.println(expectedMap.size());
        System.out.println(expectedMap.get(null));

        System.out.println("*********  Add obj with 0 key *********");
        expectedMap.put(0, "text 2");
        System.out.println(expectedMap.size());
        System.out.println(expectedMap.get(null));
        System.out.println(expectedMap.get(0));

        System.out.println("********* Keys *********");
        for (Object o : expectedMap.keySet()) {
            System.out.println(o);
        }
        System.out.println("********* Values *********");
        for (Object o : expectedMap.values()) {
            System.out.println(o);
        }

        /* Expected output
********* Add obj with NULL key *********
1
text 1
*********  Add obj with 0 key *********
2
text 1
text 2
********* Keys *********
0
null
********* Values *********
text 2
text 1
         */
    }

    public static Map getExpectedMap() {
        return new HashMap();
    }
}
