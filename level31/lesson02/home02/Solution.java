package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
        File rootFile = new File(root);
        File[] files = rootFile.listFiles();
        int i = 0;

        while (i < files.length) {
            File firstElement = files[i];
            File[] subFiles = null;

            if (firstElement.isDirectory()) {
                subFiles = firstElement.listFiles();
            }
            else {
                i++;
                continue;
            }
            File[] temp = new File[files.length + subFiles.length];
            for (int j = 0; j <= i; j++)
                temp[j] = files[j];
            for (int k = 0; k < subFiles.length; k++)
                temp[i + 1 + k] = subFiles[k];
            for (int m = i + 1; m < files.length; m++)
                temp[m + subFiles.length] = files[m];

            files = temp;
            i++;
        }

        for (File file : files) {
            if (!file.isDirectory()) result.add(file.getAbsoluteFile().toString());
        }
        return result;
    }
}
