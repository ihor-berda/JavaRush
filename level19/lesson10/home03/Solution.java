package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        String line = "";
        List<String> file = new ArrayList<String>();
        while ((line = fileReader.readLine()) != null) {
            file.add(line);
        }
        fileReader.close();
        String name = "";
        int index;
        Date date;
        for (String lines : file) {
            String[] words = lines.split(" ");
            index = words.length - 4;
            Calendar calendar = new GregorianCalendar();
            calendar.set(Integer.parseInt(words[words.length - 1]), Integer.parseInt(words[words.length - 2]) - 1,
                    Integer.parseInt(words[words.length - 3]));
            date = calendar.getTime();
            name = getName(words, index);
            name = name.substring(0, name.length() - 1);
            PEOPLE.add(new Person(name, date));
        }
    }

    public static String getName(String[] name, int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= index; i++)
        {
            builder.append(name[i]).append(" ");
        }
        return builder.toString();
    }

}
