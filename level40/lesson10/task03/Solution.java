package com.javarush.test.level40.lesson10.task03;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* В какой день недели день рождения?
Реализуй метод weekDayOfBirthday.
Метод должен возвращать день недели на итальянском языке, в который будет день рождения в определенном году.
Пример формата дат смотри в методе main.

Пример:
1) Для "30.05.1984" и "2015" метод должен вернуть: sabato
2) Для "1.12.2015" и "2016" метод должен вернуть: giovedì

Выполни задание, используя библиотеку Joda Time версии 2.9.1
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(weekDayOfBirthday("30.05.1984", "2015"));
    }

    public static String weekDayOfBirthday(String birthday, String year) {
        //напишите тут ваш код
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date d = null;
        try {
            d = format.parse(birthday);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDate ld = new LocalDate(d);
        int numberOfYears = Integer.parseInt(year) - ld.getYear();
        LocalDate localDate = ld.plusYears(numberOfYears);
        return DateTimeFormat.forPattern("EEEE").withLocale(Locale.ITALIAN).print(localDate);
    }
}
