package com.javarush.test.level31.lesson08.home01;

import java.nio.file.Files;
import java.nio.file.Paths;

/* Null Object Pattern
Почитайте на вики про паттерн "Null Object"
Используйте Files, чтобы в конструкторе класса Solution правильно инициализировать поле fileData объектом ConcreteFileData.
Если возникли какие-то проблемы со чтением файла по пути pathToFile, то инициализируйте поле объектом NullFileData.
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        try {
            boolean hidden = Files.isHidden(Paths.get(pathToFile));
            boolean executable = Files.isExecutable(Paths.get(pathToFile));
            boolean directory = Files.isDirectory(Paths.get(pathToFile));
            boolean writable = Files.isWritable(Paths.get(pathToFile));

            fileData = new ConcreteFileData(hidden, executable, directory, writable);
        }
        catch (Exception e) {
            fileData = new NullFileData(e);
        }
    }

    public FileData getFileData() {
        return fileData;
    }
}
