package com.javarush.test.level04.lesson10.task04;

/* S-квадрат
Вывести на экран квадрат из 10х10 букв S используя цикл while.
Буквы в одной строке не разделять.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        String s = "S";
        int i = 1;
        while (i <= 10)
        {
            System.out.println (s + "" + s + "" + s + "" + s + "" + s + "" + s + "" + s + "" + s + "" + s + "" + s);
            i++;

        }
    }
}
