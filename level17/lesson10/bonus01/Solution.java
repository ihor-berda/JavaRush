package com.javarush.test.level17.lesson10.bonus01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception{
        //start here - начни тут
        if (args.length < 2)
        {
            return;
        }

        if (args[0].equals("-c") && args.length >= 4)
        {
            Person person;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            int aLenght = args.length;
            String name = "";
            for (int i = 1; i < aLenght - 2; i++)
            {
                name += args[i] + " ";
            }
            name = name.substring(0, name.length() - 1);
            if (args[args.length - 2].equals("м"))
                person = Person.createMale(name, format.parse(args[args.length - 1]));
            else
                person = Person.createFemale(name, format.parse(args[args.length - 1]));
            allPeople.add(person);
            System.out.println(allPeople.indexOf(person));
        }

        else if (args[0].equals("-u") && args.length >= 5)
        {
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            int aLenght = args.length;
            String name = "";
            for (int i = 2; i < aLenght - 2; i++)
            {
                name += args[i] + " ";
            }
            name = name.substring(0, name.length() - 1);
            person.setName(name);
            if (args[args.length - 2].equals("м"))
                person.setSex(Sex.MALE);
            else
                person.setSex(Sex.FEMALE);
            person.setBirthDay(format.parse(args[args.length - 1]));
            allPeople.set(id, person);
        }

        else if (args[0].equals("-d") && args.length != 2)
        {
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
            allPeople.set(id, person);
        }
        else if (args[0].equals("-i")) {
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String sex;
            if (person.getSex() == Sex.MALE) sex = "м";
            else sex = "ж";
            System.out.println(person.getName() + " " + sex + " " + format.format(person.getBirthDay()));
        }


    }
}
