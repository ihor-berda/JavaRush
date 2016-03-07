package com.javarush.test.level29.lesson07.task02;

/* Особенности автобоксинга - 2
Исправьте ошибку реализации, приводящую к NullPointerException, в методе getValue.
Читайте доп. статью про особенности автобоксинга.
*/
public class Solution {
    public static void main(String[] args) {
        Integer a = getValue(Boolean.TRUE, Boolean.TRUE);   //100 expected
        Integer b = getValue(Boolean.FALSE, Boolean.TRUE);  //200 expected
        Integer c = getValue(Boolean.FALSE, Boolean.FALSE); //null expected

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public static Integer getValue(boolean first, boolean second) {
        if (first) return 100;
        else if (second) return  200;
        else return null;
    }
}
