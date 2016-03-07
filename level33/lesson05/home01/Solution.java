package com.javarush.test.level33.lesson05.home01;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

/* Первая сериализация в JSON
НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.6.1

1) В программе не выполнено основное требование к сериализации в JSON.
Найдите ошибку и исправьте.
2) Расставьте правильно Json аннотации у классов.
Все данные должны сериализоваться.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Cat cat = new Cat ();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 3;

        Dog dog = new Dog ();
        dog.name = "Killer";
        dog.age = 8;
        dog.owner = "Bill Jeferson";

        ArrayList<Pet> pets = new ArrayList();
        pets.add(cat);
        pets.add(dog);

        StringWriter writer = new StringWriter();
        convertToJSON(writer, pets);
        System.out.println(writer.toString());
        //[{"name":"Murka","age":5,"weight":3},{"name":"Killer","age":8,"owner":"Bill Jeferson"}]
    }

    public static void convertToJSON(StringWriter writer, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, object);
    }

    public static class Pet
    {
        public String name;
    }

    public static class Cat extends Pet
    {
        public int age;
        public int weight;
    }

    public static class Dog extends Pet
    {
        public int age;
        public String owner;
    }
}
