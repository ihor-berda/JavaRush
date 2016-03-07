package com.javarush.test.level33.lesson05.home05;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* Определение имени класса
НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.6.1

Расставьте Json аннотации так, чтобы результат выполнения метода main был следующий:
{
    "className" : ".Parking",
    "name" : "Super Parking",
    "city" : "Kyiv",
    "autos" : [ {
        "className" : "com.javarush.test.level33.lesson05.home05.RaceBike",
        "name" : "Simba",
        "owner" : "Peter",
        "age" : 2
    }, {
        "className" : "com.javarush.test.level33.lesson05.home05.Motorbike",
        "name" : "Manny",
        "owner" : null
    }, {
        "className" : "com.javarush.test.level33.lesson05.home05.Car"
    } ]
}

Подсказка: это всего два класса
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Parking parking = new Parking("Super Parking", "Kyiv");
        RaceBike raceBike = new RaceBike("Simba", "Peter", 2);
        Motorbike motorbike = new Motorbike("Manny");
        Car car = new Car();
        List<Auto> autos = new ArrayList<>();
        autos.add(raceBike);
        autos.add(motorbike);
        autos.add(car);
        parking.setAutos(autos);
        convertToJson(parking);
    }

    public static void convertToJson(Parking parking) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, parking);
        System.out.println(writer.toString());
    }
}