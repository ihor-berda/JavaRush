package com.javarush.test.level37.lesson06.task03;

import java.util.*;

/* Ambiguous behavior for NULL
Измените реализацию метода getExpectedMap, он должен вернуть объект такого класса, для которого будет противоположное
поведение при добавлении ссылки null.
См. пример вывода в методе main.
Остальной код не менять.
*/
public class Solution {
    public static void main(String[] args) {
        Map expectedMap = getExpectedMap();
        System.out.println("****** check the key \"s\" whether it IS NOT in the map");
        checkObject(expectedMap, "s");

        System.out.println("\n****** check the key \"s\" whether it IS in the map");
        expectedMap.put("s", "vvv");
        checkObject(expectedMap, "s");

        System.out.println("\n****** ambiguous behavior for NULL");
        expectedMap.put(null, null);
        checkObject(expectedMap, null);

        /*  expected output for NULL
****** ambiguous behavior for NULL
map contains the value for key = null
map does NOT contain the value for key = null
         */
    }

    public static Map getExpectedMap() {
        return new HashMap();
    }

    public static void checkObject(Map map, Object key) {
        String s1 = map.containsKey(key) ?
                "map contains the value for key = " + key : "map does NOT contain the value for key = " + key;
        System.out.println(s1);

        //if value is null, it means that the map doesn't contain the value
        Object value = map.get(key);
        String s2 = value != null ?
                "map contains the value for key = " + key : "map does NOT contain the value for key = " + key;
        System.out.println(s2);
    }
}
