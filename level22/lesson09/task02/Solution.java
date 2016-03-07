package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> pair : params.entrySet()) {
            if (pair.getValue() != null) {
                if (result.toString().equals("")) {
                    result.append(pair.getKey() + " = '" + pair.getValue() + "'");
                } else {
                    result.append(" and " + pair.getKey() + " = '" + pair.getValue() + "'");
                }
            }
        }
        return result;
    }
    public static void main(String[] args)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "Ivanov");
        params.put("country", "Ukraine");
        params.put("city", "Kiev");
        params.put("age", null);
        System.out.println(getCondition(params));
    }
}
