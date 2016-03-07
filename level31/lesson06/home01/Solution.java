package com.javarush.test.level31.lesson06.home01;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        boolean exist = false;
        String fileName = args[0];
        String zipName = args[1];
        Map<ZipEntry, byte[]> map = new HashMap<>();

        String fileWithoutPathName = new File(fileName).getName();

        FileInputStream fis = new FileInputStream(zipName);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
        ZipEntry ze;
        while ((ze = zis.getNextEntry()) != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count;
            while ((count = zis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            map.put(ze, baos.toByteArray());
        }
        zis.close();

        FileOutputStream fos = new FileOutputStream(zipName);
        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
        for (Map.Entry<ZipEntry, byte[]> pair : map.entrySet()) {
            if (fileWithoutPathName.equals(pair.getKey().getName())) {
                exist = true;
                continue;
            }
            zos.putNextEntry(pair.getKey());
            zos.write(pair.getValue());
            zos.closeEntry();
        }

        if (exist) {
            FileInputStream fis2 = new FileInputStream(fileName);
            byte[] fileBytes = new byte[fis2.available()];
            fis2.read(fileBytes);
            fis2.close();

            ZipEntry zipEntry = new ZipEntry("new/" + fileWithoutPathName);
            zos.putNextEntry(zipEntry);
            zos.write(fileBytes);
            zos.closeEntry();
            zos.close();
        }
        else {
            zos.closeEntry();
            zos.close();
        }
    }
}