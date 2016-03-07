package com.javarush.test.level19.lesson03.task02;

/* Адаптер
Используйте класс AdapterFileOutputStream, чтобы адаптировать FileOutputStream к новому интерфейсу AmigoStringWriter
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter {

    private FileOutputStream fos;
    AdapterFileOutputStream(FileOutputStream fos) {
        this.fos = fos;
    }
    @Override
    public void flush() throws IOException
    {
        this.fos.flush();
    }

    @Override
    public void writeString(String s) throws IOException
    {
        this.fos.write(s.getBytes());
    }

    @Override
    public void close() throws IOException
    {
        this.fos.close();
    }
}

