package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        //add your code here
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        String url = in.readLine();
        url = url.substring(url.indexOf("?")+1,url.length());
        List<String> params = new ArrayList<String>(Arrays.asList(url.split("&")));

        String parValue = "";
        for (int i = 0; i<params.size(); i++) {
            String str = params.get(i);
            if (str.contains("=")) {
                String parName = str.substring(0,str.indexOf("="));
                if (i != 0) System.out.print(" ");
                System.out.print(parName);
                if ("obj".equals(parName)){
                    parValue = str.substring(str.indexOf("=")+1,str.length());
                }
            }
            else {
                if (i != 0) System.out.print(" ");
                System.out.print(str);
            }
        }
        if (! parValue.isEmpty()) {
            System.out.println("");
            try{
                alert (Double.valueOf(parValue));
            }
            catch (Exception e){
                alert (parValue);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
