package com.javarush.test.level38.lesson08.task02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/* Неверные аннотации
Исправь неверные аннотации. Код должен компилировался без ошибок и предупреждений.
*/


@Target(ElementType.METHOD)
@interface Main {
}

public class Solution {
    @Main
    public static void main(String[] args) {
        Solution solution = new Solution().new SubSolution();
        solution.overriddenMethod();
    }

    public void overriddenMethod() {
    }

    public class SubSolution extends Solution {
        @Override
        public void overriddenMethod() {
            System.out.println(uncheckedCall());
        }
        @SuppressWarnings("unchecked")
        List uncheckedCall() {
            List list = new ArrayList();
            list.add("hello");
            return list;
        }
    }


}
