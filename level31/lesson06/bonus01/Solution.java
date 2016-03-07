package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File resultFileName = new File(args[0]);
        byte[] arr = new byte[1024];
        List<File> list = new ArrayList<>();
        for (int i = 1; i <= args.length-1; i++) {
            list.add(new File(args[i]));
        }
        Collections.sort(list);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (File f : list) {
            FileInputStream fis = new FileInputStream(f);
            while (fis .read(arr)>-1) {
                baos.write(arr);
                baos.flush();
            }
        }
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(baos.toByteArray()));
        FileOutputStream fos = new FileOutputStream(resultFileName);
        ZipEntry ze;
        while ((ze = zis.getNextEntry()) != null) {
            int count;
            while ((count = zis.read(arr)) != -1) {
                fos.write(arr, 0, count);
                fos.flush();
            }
        }
        fos.close();
    }
}
