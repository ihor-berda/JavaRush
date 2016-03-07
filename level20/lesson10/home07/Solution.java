package com.javarush.test.level20.lesson10.home07;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправьте ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные  - writeObject
3) сериализовать класс Solution  - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable {
    private static final long serialVersionUID = 1;
    public static String fileName;
    public static void main(String[] args) throws Exception
    {
        Solution solution = new Solution("f:/1.txt");
        solution.writeObject("lala");
//        solution.close();
        FileOutputStream fos = new FileOutputStream("f:/2.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        objectOutputStream.writeObject(solution);
//        objectOutputStream.close();
//        fos.close();
        FileInputStream fis = new FileInputStream("f:/2.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fis);
        Solution solution2 = (Solution) objectInputStream.readObject();
//        fis.close();
        solution2.writeObject("haha");
//        solution2.close();
    }

    transient private FileOutputStream stream;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }
}
