package com.javarush.test.level33.lesson05.home02;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyName;

import java.io.IOException;
import java.io.StringWriter;

/* Вторая сериализация в JSON
НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.6.1

Расставьте Json аннотации так, чтобы результат работы метода main был следующим
{"wildAnimal":"Murka","over":3}
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 3;

        StringWriter writer = new StringWriter();
        convertToJSON(writer, cat);
        System.out.println(writer.toString());
    }

    public static void convertToJSON(StringWriter writer, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, object);
    }

    @JsonAutoDetect
    public static class Cat {
        @JsonProperty(value="wildAnimal")
        public String name;
        @JsonIgnore
        public int age;
        @JsonProperty(value="over")
        public int weight;

        Cat() {
        }
    }
}
