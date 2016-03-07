package com.javarush.test.level30.lesson06.task01;

import java.util.ArrayList;
import java.util.List;

/* Такие хитрые исключения!
Исправьте реализацию метода checkAFlag, чтобы во всех случаях он не приводил к бросанию исключений.
Сохраните логику вывода данных.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        checkAFlag(new D());
    }

    public static void checkAFlag(D d) {
        if(d != null && d.cs != null && !d.cs.isEmpty() && d.cs.get(0) != null &&
                d.cs.get(0).bs != null && !d.cs.get(0).bs.isEmpty() && d.cs.get(0).bs.get(0) != null &&
                d.cs.get(0).bs.get(0).as != null && !d.cs.get(0).bs.get(0).as.isEmpty() &&
                d.cs.get(0).bs.get(0).as.get(0) != null && d.cs.get(0).bs.get(0).as.get(0).flag){
            System.out.println("A's flag is true");
        } else { //all other cases
            System.out.println("Oops!");
        }
    }

    static class A {
        boolean flag = true;
    }

    static class B {
        List<A> as = new ArrayList<>();
        {
            as.add(new A());
        }
    }

    static class C {
        List<B> bs = new ArrayList<>();
        {
            bs.add(new B());
        }
    }

    static class D {
        List<C> cs = new ArrayList<>();
        {
            cs.add(new C());
        }
    }
}
