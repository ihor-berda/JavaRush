package com.javarush.test.level33.lesson05.home05;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;
@JsonAutoDetect
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
@JsonSubTypes(@JsonSubTypes.Type(value=Parking.class,  name=".Parking"))
public class Parking {
    public String name;
    public String city;

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,  property="className")
    public List<Auto> autos;

    public Parking(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", autos=" + autos +
                '}';
    }
}