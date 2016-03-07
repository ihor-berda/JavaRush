package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<String> fileNameSet = new TreeSet<String>(new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                int k = Integer.parseInt(o1.substring(o1.indexOf(".part") + 5));
                int j = Integer.parseInt(o2.substring(o2.indexOf(".part") + 5));
                if (k > j) return 1;
                else if (k < j) return -1;
                else return 0;
            }
        });
        String fileName = reader.readLine();
        String originalFileName = fileName;
        while (!fileName.equals("end")){
            fileNameSet.add(fileName);
            fileName = reader.readLine();
        }
        String newFileName = originalFileName.substring(0, originalFileName.indexOf(".part"));
        FileOutputStream fos = new FileOutputStream(newFileName);
        for (String s : fileNameSet) {
            FileInputStream fis = new FileInputStream(s);
            byte[] bytes = new byte[fis.available()];
            while (fis.available() > 0) {
                fis.read(bytes);
                fos.write(bytes);
            }
            fis.close();
        }
        reader.close();
        fos.close();
    }
}
