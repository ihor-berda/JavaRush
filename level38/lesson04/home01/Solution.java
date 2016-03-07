package com.javarush.test.level38.lesson04.home01;

/* Исправь ошибки в коде
Исключения NameIsEmptyException и NameIsNullException должны быть checked.
Все типы исключений должны быть обработаны.
Реализацию методов main() и getNumberOfCharacters() не менять.
*/

public class Solution {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                System.out.println("Имя содержит " + NameChecker.getNumberOfCharacters(args[0]) + " символов");
            } catch (Exception e) {
                System.out.println(e.toString());
            } catch (NameIsNullException e) {
                System.out.println("Ошибка: Имя не задано");
            } catch (NameIsEmptyException e) {
                System.out.println("Ошибка: Имя пустое");
            }
        }
    }
}
