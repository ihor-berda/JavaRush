package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution
{
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName;
            while (true) {
                fileName = reader.readLine();
                if (fileName.equals("exit")) break;
                new ReadThread(fileName).start();
            }
            for (Map.Entry<String, Integer> pair : resultMap.entrySet()) {
                System.out.println(pair.getKey() + " " + pair.getValue());
            }
        }
        catch (Exception e) {}
    }

    public static class ReadThread extends Thread
    {
        String fileName;
        public ReadThread(String fileName)
        {
            //implement constructor body
            this.fileName = fileName;
        }
            // implement file reading here - реализуйте чтение из файла тут

        public void run()
        {
            try
            {
                FileInputStream in = new FileInputStream(fileName);
                Map<Integer, Integer> list = new HashMap<Integer, Integer>();
                while (in.available() > 0) {
                    int key = in.read();
                    if (list.containsKey(key)) {
                        int value = list.get(key);
                        list.put(key, ++value);
                    }
                    else list.put(key, 1);
                }
                int tempValue=0;
                for(Map.Entry<Integer, Integer> pair : list.entrySet()){
                    if(tempValue < pair.getValue())
                        tempValue = pair.getValue();
                }
                for(Map.Entry<Integer, Integer> pair : list.entrySet()) {
                    if (pair.getValue() == tempValue)
                        resultMap.put(fileName, pair.getKey());
                }
            }
            catch (Exception e) {}
        }
    }
}

