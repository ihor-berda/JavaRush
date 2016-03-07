package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scanner = new Scanner("f:/1.txt");
        PersonScannerAdapter (Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            String line = scanner.nextLine();
            String[] lines = line.split(" ");
            Calendar calendar = new GregorianCalendar(Integer.parseInt(lines[5]), Integer.parseInt(lines[4]) - 1, Integer.parseInt(lines[3]));
            Date date = calendar.getTime();
            Person person = new Person(lines[1], lines[2], lines[0], date);
            return person;
        }

        @Override
        public void close() throws IOException
        {
            this.scanner.close();
        }
    }
}
