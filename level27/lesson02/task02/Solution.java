package com.javarush.test.level27.lesson02.task02;

/* Нужный оператор
Вставьте в код единственную строчку - оператор (не break), чтобы выводился треугольник из букв S
*/
public class Solution {
    public static void main(String args[]) {
        label:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j > i) {
                    System.out.println();
                    continue label;
                }
                System.out.print("S");
            }
        }
    }
}
