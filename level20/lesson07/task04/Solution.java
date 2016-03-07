package com.javarush.test.level20.lesson07.task04;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Serializable Solution
Сериализуйте класс Solution.
Подумайте, какие поля не нужно сериализовать, пометить ненужные поля — transient.
Объект всегда должен содержать актуальные на сегодняшний день данные.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать файл, открыть поток на чтение (input stream) и на запись(output stream)
2) создать экземпляр класса Solution - savedObject
3) записать в поток на запись savedObject (убедитесь, что они там действительно есть)
4) создать другой экземпляр класса Solution с другим параметром
5) загрузить из потока на чтение объект - loadedObject
6) проверить, что savedObject.string равна loadedObject.string
7) обработать исключения
*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream("f:/1.txt");
            outputStream = new FileOutputStream("f:/1.txt");
        } catch (FileNotFoundException e) {}
        Solution savedObject = new Solution(10);
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println(savedObject.toString());
        writer.close();
        System.out.println(savedObject.toString());
        Solution loadedObject = new Solution(20);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            loadedObject.string = reader.readLine();
            reader.close();
        }
        catch (IOException e) {}
        System.out.println(loadedObject.string);
        if (savedObject.string.equals(loadedObject.string)) {
            System.out.println(new Solution(4));
        }
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
