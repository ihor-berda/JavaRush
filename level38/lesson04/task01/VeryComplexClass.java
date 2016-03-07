package com.javarush.test.level38.lesson04.task01;

/* Проверяемые исключения (checked exception)
Напиши реализацию метода veryComplexMethod().
Он должен всегда кидать какое-нибудь проверяемое исключение.
Кинуть исключение (throw) явно нельзя.
*/

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        int i = 7 / 0;
    }
}
