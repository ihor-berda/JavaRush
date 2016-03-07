package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.*;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(reader.readLine());
            load(inputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (inputStream == null)
                {
                    inputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(OutputStream outputStream)  {
        Properties prop = new Properties();
        for(Map.Entry<String,String> entry: properties.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            prop.setProperty(key,value);
        }
        try
        {
            prop.store(outputStream, null);
            outputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(InputStream inputStream) {
        Properties prop = new Properties();
        try
        {
            prop.load(inputStream);
        }
        catch (IOException ed) {
            ed.printStackTrace();
        }
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            properties.put(key,value);
        }
        try
        {
            inputStream.close();
        }
        catch (IOException ee) {
            ee.printStackTrace();
        }
    }
}
