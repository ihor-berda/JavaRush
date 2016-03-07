package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        String productName = "";
        for (int i = 1; i < args.length - 2; i++)
        {
            productName = productName + args[i] + " ";
        }
        String id = getId(fileName);
        PrintWriter fileWritter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        StringBuilder prn = new StringBuilder(productName);
        prn.setLength(30);
        for (int i = 0; i < prn.length(); i++)
        {
            if (prn.charAt(i) == '\u0000') prn.setCharAt(i, ' ');
        }        StringBuilder price = new StringBuilder(args[args.length - 2]);
        price.setLength(8);
        for (int i = 0; i < price.length(); i++)
        {
            if (price.charAt(i) == '\u0000') price.setCharAt(i, ' ');
        }
        StringBuilder quantity = new StringBuilder(args[args.length - 1]);
        quantity.setLength(4);
        for (int i = 0; i < quantity.length(); i++)
        {
            if (quantity.charAt(i) == '\u0000') quantity.setCharAt(i, ' ');
        }
        String result = String.format("%1$-1.8s%2$-1.30s%3$-1.8s%4$-1.4s", id, prn, price,
                quantity);
        fileWritter.println(result);
        fileWritter.close();
    }


    public static String getId (String fileName) throws IOException {
        ArrayList<Long> allIds = new ArrayList<Long>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line=bufferedReader.readLine()) != null) {
            line = line.substring(0, 8).replaceAll("\\s", "");
            Long currentId = Long.parseLong(line);
            allIds.add(currentId);
        }
        bufferedReader.close();
        Long maxId = Collections.max(allIds);
        Long MyId = maxId+1;
        return MyId.toString();
    }
}
