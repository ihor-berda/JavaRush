package com.javarush.test.level09.lesson08.task02;

/* Перехватывание исключений
1. Есть три исключения последовательно унаследованные от Exception:
2. class Exception1 extends  Exception
3. class Exception2 extends  Exception1
4. class Exception3 extends  Exception2
5. Есть метод, который описан так:
5.1. public static void method1() throws Exception1, Exception2, Exception3

6. Напиши catch, который перехватит все три Exception1, Exception2 и Exception3
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
      //напишите тут ваш код
        try
        {
            method1();
        }
        catch (Exception1 e)
        {

        }



        //напишите тут ваш код

    }

    public static void method1() throws Exception1, Exception2, Exception3
    {
        int i = (int) (Math.random() * 3);
        if (i == 0)
            throw new Exception1();
        if (i == 1)
            throw new Exception2();
        if (i == 2)
            throw new Exception3();
    }
}

class Exception1 extends Exception {
}

class Exception2 extends Exception1 {
}

class Exception3 extends Exception2 {
}